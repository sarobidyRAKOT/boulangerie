
# Étape 1 : Utiliser une image de base OpenJDK Alpine pour la construction
FROM openjdk:8-jdk-alpine as build

# Définir les variables d'environnement
ENV APP_HOME /usr/local/boulangerie
ENV WILDFLY_VERSION 10.1.0.Final
ENV APP_NAME boulangerie

# Installer les dépendances nécessaires
RUN apk update && \
    apk add --no-cache \
    postgresql-client \
    apache-ant \
    bash \
    curl \
    tar && \
    rm -rf /var/cache/apk/*

# Définir le répertoire de travail pour le projet
WORKDIR ${APP_HOME}
COPY . ${APP_HOME}/

# Compilation avec apache-ant
RUN ant -f ${APP_HOME}/build.xml

# Étape 2 : Télécharger et installer WildFly (uniquement si nécessaire)
RUN if [ ! -d /opt/wildfly ]; then \
    wget https://download.jboss.org/wildfly/${WILDFLY_VERSION}/wildfly-${WILDFLY_VERSION}.tar.gz -O /tmp/wildfly.tar.gz && \
    tar -xzvf /tmp/wildfly.tar.gz -C /opt && \
    mv /opt/wildfly-${WILDFLY_VERSION} /opt/wildfly && \
    rm /tmp/wildfly.tar.gz; \
fi

# Copier le fichier WAR généré dans le répertoire de déploiement de WildFly
RUN cp ${APP_HOME}/dist/${APP_NAME}.war /opt/wildfly/standalone/deployments/

EXPOSE 8080 5432

CMD ["/opt/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]

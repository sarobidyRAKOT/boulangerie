<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  ArrayList <Categorie> categs = (ArrayList<Categorie>) request.getAttribute("categs");
  ArrayList <Unite> unites = (ArrayList<Unite>) request.getAttribute("unites");
%>

<div class="card">
  <div class="card-body">
    <h4 class="card-title">PRODUITS</h4>
    <p class="card-description">
      Page d'insertion PRODUITS
    </p>
    <form action="${pageContext.request.contextPath}/insertion_produit" method="POST" class="forms-sample">
      <div class="form-group">
        <label>Nom du produit</label>
        <input type="text" name="nom" class="form-control form-control-lg" placeholder="Nom du produit" aria-label="Nom du produit">
      </div>
      <div class="form-group">
        <label>Prix du produit (en ariary)</label>
        <input type="number" name="prix" class="form-control form-control-lg" placeholder="prix du produit" aria-label="prix">
       </div>
      <div class="form-group">
        <label for="form-select">Choisir categorie</label>
        <select name="id_categorie" class="form-control form-control-lg" id="form-select">
          <% for (Categorie c : categs) { %>
          <option value="<%=c.getId()%>"><%= c.getNom()%></option>
          <% } %>
        </select>
      </div>
      <div class="form-group">
        <label for="form-select">Choisir unite</label>
        <select name="id_unite" class="form-control form-control-lg" id="form-select">
          <% for (Unite u : unites) { %>
          <option value="<%=u.getId()%>"><%= u.getNom()%> - <%=u.getRef()%></option>
          <% } %>
        </select>
      </div>

        <button type="submit" class="btn btn-lg btn-success mr-2">Ajouter produit</button>
      <a href="${pageContext.request.contextPath}">
        <button type="button" class="btn btn-lg btn-info mr-2">Retour</button>
      </a>
    </form>
  </div>
</div>
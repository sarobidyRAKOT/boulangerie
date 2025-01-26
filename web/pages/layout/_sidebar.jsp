      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">
              <i class="icon-layout menu-icon  mt-n2 mt-n2"></i>
              <span class="menu-title">Dashboard</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#insertion" aria-expanded="false" aria-controls="insertion">
              <i class="ti-plus menu-icon  mt-n2"></i>
              <span class="menu-title">Insertions</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="insertion">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/basiques"> Basiques </a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/formule"> Formule </a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/produitMois">Produit du mois</a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/vente_venteDetail">vente</a></li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#etats" aria-expanded="false" aria-controls="etats">
              <i class="ti-bar-chart menu-icon  mt-n2"></i>
              <span class="menu-title">Etats</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="etats">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href=""> Stocks </a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href=""> Benefice </a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/etatCommission"> Commission </a></li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/choisir_produit">
              <i class="ti-settings menu-icon  mt-n2"></i>
              <span class="menu-title">Productions</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#listes" aria-expanded="false" aria-controls="listes">
              <i class="ti-list menu-icon  mt-n2"></i>
              <span class="menu-title">Listes</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="listes">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/listeProduction"> Productions </a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/listeIngredient"> Ingerients </a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/listeProduit"> Produits </a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/listeVente"> Ventes </a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/listeVenteClient"> Achats clients </a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/listeHistoriqueProduitsMois2024"> Produits conseillee 2024 </a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/listeCommission"> Commission </a></li>
                
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#achat-vente" aria-expanded="false" aria-controls="achat-vente">
              <i class="ti-credit-card menu-icon  mt-n2"></i>
              <span class="menu-title">Achats / Ventes</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="achat-vente">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/vente_encours">Voir vente en cours</a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/achat"> Achats ingerients </a></li>
                <li class="nav-item my-n1"><a class="nav-link mt-2 mr-3" href="${pageContext.request.contextPath}/panier">Voir panier</a></li>
              </ul>
            </div>
          </li>

        </ul>
      </nav>
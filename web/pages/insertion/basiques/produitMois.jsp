<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.*" %> 

<%
  HashMap <String, String> mois = (HashMap <String, String>) request.getAttribute ("mois");
  ArrayList <Produit> produits = (ArrayList<Produit>) request.getAttribute("produits");
%>

<div class="card">
  <div class="card-body">
    <h4 class="card-title">PRODUITS DU MOIS</h4>
    <p class="card-description">
      Page d'insertion PRODUITS DU MOIS
    </p>
    <form action="${pageContext.request.contextPath}/insertion_produitMois" method="POST" class="forms-sample">
      <div class="form-group">
        <label for="form-select">Choisir Mois</label>
        <select name="mois" class="form-control form-control-lg" id="form-select">
          <% for (String cle : mois.keySet()) { %>
          <option value="<%=cle%>"><%=mois.get(cle)%></option>
          <% } %>   
        </select>
      </div>
      <div class="form-group">
        <label for="form-select">Choisir Produit</label>
        <select name="id_produit" class="form-control form-control-lg" id="form-select">
          <% for (Produit produit : produits) { %>
          <option value="<%=produit.getId()%>"><%=produit.getNom()%></option>
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
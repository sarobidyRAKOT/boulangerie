<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  ArrayList<Produit> produits = (ArrayList<Produit>) request.getAttribute("produits");
%>

<div class="card">
  <div class="card-body">
    <h2 class="card-title">HISTORIQUE PRIX DE VENTE PRODUITS</h2>
    <p class="card-description">
      Page d'insertion HISTORIQUE PRIX DE VENTE PRODUITS
    </p>
    <form action="${pageContext.request.contextPath}/insertion_PrixVente" method="POST" class="forms-sample">

      <div class="form-group row">
        <label class="col-sm-3 col-form-label">Gender</label>
        <div class="col-sm-9">
          <select name="id_produit" class="form-control">
            <% for (Produit p : produits) { %>
            <option value="<%=p.getId()%>"><%= p.getNom()%></option>
            <% } %>
          </select>
        </div>
      </div>
      <div class="form-group row">
        <label for="daty" class="col-sm-3 col-form-label">Date modification</label>
        <div class="col-sm-9">
          <input name="daty" type="date" class="form-control" id="daty" placeholder="Date modification" required>
        </div>
      </div>
      <div class="form-group row">
        <label for="nvPrix" class="col-sm-3 col-form-label">Nouveau prix du produit</label>
        <div class="col-sm-9">
          <input name="prix" type="text" class="form-control" id="nvPrix" placeholder="Nouveau prix du produit" required>
        </div>
      </div>
      <div class="form-group row">
        <label for="text" class="col-sm-3 col-form-label">Motif</label>
        <div class="col-sm-9">
          <textarea name="motif" class="form-control" id="text" rows="2"></textarea>
        </div>
      </div>

      <button type="submit" class="btn btn-lg btn-success mr-2">Valider</button>
      <a href="${pageContext.request.contextPath}">
        <button type="button" class="btn btn-lg btn-info mr-2">Retour</button>
      </a>
    </form>
  </div>
</div>
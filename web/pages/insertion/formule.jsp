<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  ArrayList <Produit> produits = (ArrayList<Produit>) request.getAttribute("produits");
%>

<div class="row">
  <div class="col-md-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Formule de production</h4>
        <p class="card-description">
          Ajouter un autre formule de production
        </p>
        <form method="POST" action="${pageContext.request.contextPath}/insertion_formule" class="forms-sample">
          <div class="form-group">
            <label for="form-select">Date creation formule</label>
            <input type="date" name="daty" class="form-control form-control-lg" id="form-select" placeholder="Date formule">
          </div>
          <div class="form-group">
            <label for="textarea">Description</label>
            <textarea name="description" class="form-control" id="textarea" rows="2"></textarea>
          </div>
          <div class="form-group">
            <label for="form-input">Quantite produite</label>
            <input type="number" name="quantite_produite" class="form-control form-control-lg" id="form-input" placeholder="Quantite produite">
          </div>
          <div class="form-group">
            <label for="form-select">Choisir produit</label>
            <select name="id_produit" class="form-control form-control-lg" id="form-select">
              <% for (Produit p : produits) { %>
              <option value="<%=p.getId()%>"><%= p.getNom()%> - <%=p.getPrix()%></option>
              <% } %>
            </select>
          </div>

          <button type="submit" class="btn btn-lg btn-success mr-2">Suivant</button>
          <a href="${pageContext.request.contextPath}">
            <button type="button" class="btn btn-lg btn-info mr-2">Retour</button>
          </a>
        </form>
      </div>
    </div>
  </div>
</div>
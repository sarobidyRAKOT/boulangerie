<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  ArrayList <Categorie> categories = (ArrayList<Categorie>) request.getAttribute("categories");
  ArrayList <Ingredient> ingredients = (ArrayList<Ingredient>) request.getAttribute("ingredients");
%>

<div class="row">
  <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Recherche <b>PRODUITS</b></h4>
        <form action="${pageContext.request.contextPath}/liste_byCategorieIngredient" method="POST" class="forms-sample">

          <div class="form-group">
            <label for="form-select">Choisir categorie</label>
            <select name="id_categorie" class="form-control form-control-lg" id="form-select">
              <% for (Categorie categorie : categories) { %>
              <option value="<%=categorie.getId()%>"><%=categorie.getNom()%></option>
              <% } %>
            </select>
          </div>
          <div class="form-group">
            <label for="form-select">Choisir ingredient</label>
            <select name="id_ingredient" class="form-control form-control-lg" id="form-select">
              <% for (Ingredient ingredient : ingredients) { %>
              <option value="<%=ingredient.getId()%>"><%=ingredient.getNom()%></option>
              <% } %>
            </select>
          </div>
          <button type="submit" class="btn btn-lg btn-success mr-2">Lister produit</button>
          <a href="${pageContext.request.contextPath}">
            <button type="button" class="btn btn-lg btn-info mr-2">Retour</button>
          </a>
        </form>
      </div>
    </div>
  </div>
</div>
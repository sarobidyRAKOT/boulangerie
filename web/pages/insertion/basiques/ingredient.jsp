<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  ArrayList <Unite> unites = (ArrayList<Unite>) request.getAttribute("unites");
%>

<div class="card">
  <div class="card-body">
    <h4 class="card-title">INGREDIENTS</h4>
    <p class="card-description">
      Page d'insertion INGREDIENTS
    </p>
    <form action="${pageContext.request.contextPath}/insertion_ingredient" method="POST" class="forms-sample">
      <div class="form-group">
        <label>Non ingredient</label>
        <input type="text" name="nom" class="form-control form-control-lg" placeholder="Non de l'ingredient" aria-label="Non de l'ingredient">
      </div>
      <div class="form-group">
        <label for="form-select">Choisir unite</label>
        <select name="id_unite" class="form-control form-control-lg" id="form-select">
          <% for (Unite u : unites) { %>
          <option value="<%=u.getId()%>"><%= u.getNom()%> - <%=u.getRef()%></option>
          <% } %>
        </select>
      </div>
      <button type="submit" class="btn btn-lg btn-success mr-2">Ajouter Ingredients</button>
      <a href="${pageContext.request.contextPath}">
        <button type="button" class="btn btn-lg btn-info mr-2">Retour</button>
      </a>
    </form>
  </div>
</div>
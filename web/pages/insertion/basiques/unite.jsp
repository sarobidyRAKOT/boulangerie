<%-- <%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  // ArrayList <Produit> produits = (ArrayList<Produit>) request.getAttribute("produits");
%> --%>

<div class="card">
  <div class="card-body">
    <h4 class="card-title">UNITE</h4>
    <p class="card-description">
      Page d'insertion UNITE.
    </p>
    <form method="POST" action="${pageContext.request.contextPath}/insertion_categorie" class="forms-sample">
      <div class="form-group">
        <label for="form-control">Libele Unite</label>
        <input type="text" name="nom" class="form-control form-control-lg" id="form-control" placeholder="Libele Unite">
      </div>
      <div class="form-group">
        <label for="form-control">Reference Unite</label>
        <input type="text" name="ref" class="form-control form-control-lg" id="form-control" placeholder="Reference">
      </div>

      <button type="submit" class="btn btn-lg btn-success mr-2">Suivant</button>
      <a href="${pageContext.request.contextPath}/basiques">
        <button type="button" class="btn btn-lg btn-info mr-2">Retour</button>
      </a>
    </form>
  </div>
</div>
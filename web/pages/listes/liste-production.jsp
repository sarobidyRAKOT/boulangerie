<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  // ArrayList <Produit> produits = (ArrayList<Produit>) request.getAttribute("produits");
%>

<div class="row">

  <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Recherche PRODUCTIONS</h4>
        <form action="${pageContext.request.contextPath}/insertion_production" method="POST" class="forms-sample">
          <div class="form-group">
            <label>Quantite ingredient usuel en n fois</label>
            <input type="number" name="n_ingredient" class="form-control form-control-lg" placeholder="Quantite ingredient multiplie par ..." aria-label="Quantite ingredient multiplie par ...">
          </div>
          <div class="form-group">
            <label>Quantite produits produitent</label>
            <input type="number" name="quantite_produite" class="form-control form-control-lg" placeholder="Quantite produit produitent" aria-label="Quantite produit produitent">
          </div>
          <div class="form-group">
            <label>Date production</label>
            <input type="date" name="daty" class="form-control form-control-lg" placeholder="Date production" aria-label="Date production">
          </div>
          <button type="submit" class="btn btn-lg btn-success mr-2">Suivant</button>
          <a href="${pageContext.request.contextPath}">
            <button type="button" class="btn btn-lg btn-info mr-2">Retour</button>
          </a>
          <a href="${pageContext.request.contextPath}">
            <button type="button" class="btn btn-lg btn-danger mr-2">Annuler</button>
          </a>
        </form>
      </div>
    </div>
  </div>


  <div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Listes Produits</h4>
        <p class="card-description">
          Listes de tous les Produits
        </p>
        <div class="table-responsive pt-3">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Date production</th>
                <th>Quantites produite</th>
                <th>Unite</th>
                <th>Prix production</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>2023-12-30</td>
                <td>12</td>
                <td>Pieces</td>
                <td>200 000</td>
                <td>
                  <a class="btn btn-sm btn-info" href="${pageContext.request.contextPath}/">Affichee details</a>
                </td>
              </tr>
              <%-- <tr>
                <td><%=produit.getId()%></td>
                <td><%=produit.getNom()%></td>
                <td><%=produit.getNom_categorie()%></td>
                <td><%=produit.getPrix()%></td>
              </tr> --%>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>
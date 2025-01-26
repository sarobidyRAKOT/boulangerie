<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.*" %> 
<%@page import="java.time.LocalDate" %> 

<%

  ArrayList <Produit> produits = (ArrayList<Produit>) request.getAttribute("produits");
%>

<div class="row">

  <div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Listes Produits du mois 2424</h4>
        <p class="card-description">
          Listes de tous les Produits
        </p>
        <div class="table-responsive pt-3">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Nom produits</th>
                <th>id Categorie</th>
                <th>id unite</th>
              </tr>
            </thead>
            <tbody>
              <% for (Produit produit : produits) { %>
              <tr>
                <td><%=produit.getId()%></td>
                <td><%=produit.getNom()%></td>
                <td><%=produit.getId_categorie()%></td>
                <td><%=produit.getId_unite()%></td>
              </tr>
              <% } %>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>
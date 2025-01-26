<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  ArrayList <Produit> produits = (ArrayList<Produit>) request.getAttribute("produits");
%>

<div class="row">
  <div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Listes produits</h4>
        <p class="card-description">
          Listes des produits avec<code> Quantite</code> en stock
        </p>
        <div class="table-responsive pt-3">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Nom produit</th>
                <th>Categorie</th>
                <th>Prix</th>
                <th>Quantite</th>
                <th>Unite</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <% for (Produit produit : produits) { %>
              <tr>
                <td><%=produit.getId()%></td>
                <td><%=produit.getNom()%></td>
                <td><%=produit.getNom_categorie()%></td>
                <td><%=produit.getPrix()%> ariary</td>
                <td><%=produit.getQuantite()%></td>
                <td><%=produit.getNom_unite()%></td>
                <td>
                  <a class="btn btn-sm btn-block btn-success" href="${pageContext.request.contextPath}/formuleProduit?id_produit=<%=produit.getId()%>">
                    Choisir
                  </a>
                </td>
              </tr>
              <% } %>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>
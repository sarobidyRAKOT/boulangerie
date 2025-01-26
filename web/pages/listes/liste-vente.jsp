<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
    ArrayList<Vente_detail> vente_details = (ArrayList<Vente_detail>) request.getAttribute("vente_details");  
    ArrayList<Categorie> categories = (ArrayList<Categorie>) request.getAttribute("categories");  
    int p = (int)  request.getAttribute("page");  
%>


<div class="row">

  <div class="col-md-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Rechercher par <b>CATEGORIE</b></h4>
        <form action="${pageContext.request.contextPath}/listeVente" method="POST" class="forms-sample">
          <div class="form-group">
            <label for="form-select">Choisir categorie</label>
            <select name="id_categorie" class="form-control form-control-lg" id="form-select">
              <option value="">Tous</option>
              <% for (Categorie categorie : categories) { %>
              <option value="<%=categorie.getId()%>"><%= categorie.getNom()%></option>
              <% } %>
            </select>
          </div>
          <button type="submit" class="btn btn-lg btn-success mr-2">Rechercher</button>
        </form>
      </div>
    </div>
  </div>

  <div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Listes des ventes</h4>
        <p class="card-description">
          Listes ventes details
        </p>
        <div class="table-responsive pt-3">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Vente</th>
                <th>Nom produit</th>
                <th>Quantite produit</th>
                <th>Unite</th>  
                <th>prix unitaire</th>
                <th>Categorie</th>  
              </tr>
            </thead>
            <tbody>
              <% for (Vente_detail vente_detail : vente_details) { %>  
              <tr>
                <td><%=vente_detail.getId()%></td>
                <td><%=vente_detail.getVente()%></td>
                <td><%=vente_detail.getNom_produit()%></td>
                <td><%=vente_detail.getQuantite_produit()%></td>
                <td><%=vente_detail.getUnite()%></td>
                <td><%=vente_detail.getPrix_unitaire()%></td>
                <td><%=vente_detail.getCategorie()%></td>
              </tr>
              <% } %>
            </tbody>
          </table>
          <div class="template-demo">
            <div class="btn-group mt-4" role="group" aria-label="Basic example">
                <a href="${pageContext.request.contextPath}/listeVente?page=<%=(p-1)%>" class="btn btn-outline-secondary">precedent </a>
                <a href="" class="btn btn-outline-secondary"> ... </a>
                <a href="${pageContext.request.contextPath}/listeVente?page=<%=(p+1)%>" class="btn btn-outline-secondary">suivant</a>
            </div>
            <div class="d-flex align-items-center justify-content-center" style="">
              <p>page <%=p%></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
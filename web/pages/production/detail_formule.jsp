<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  String id_produit = (String) request.getAttribute ("id_produit");
  String id_formule = (String) request.getAttribute ("id_formule");
  ArrayList <Formule_detaile> formule_detailes = (ArrayList<Formule_detaile>) request.getAttribute("formule_detailes");
%>


<div class="row">
  <div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Details formule</h4>
        <p class="card-description">
          Details de la formule <code>1</code>
        </p>
        <div class="table-responsive pt-3">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Nom ingredient</th>
                <th>Quantite</th>
                <th>Unite</th>
              </tr>
            </thead>
            <tbody>
              <% for (Formule_detaile formule_detaile : formule_detailes) { %>
              <tr>
                <td><%=formule_detaile.getId_ingredient()%></td>
                <td><%=formule_detaile.getNom_ingredient()%></td>
                <td><%= formule_detaile.getQuantite_ingredient()%></td>
                <td><%=formule_detaile.getNom_unite()%></td>
              </tr>
              <% } %>
            </tbody>
          </table>
        </div>

        <a href="${pageContext.request.contextPath}/formuleProduit?id_produit=<%=id_produit%>">
          <button type="button" class="btn btn-lg mr-3 mt-4 btn-info">Retour</button>
        </a>
        <a href="${pageContext.request.contextPath}/production?id_formule=<%=id_formule%>">
          <button type="button" class="btn btn-lg mr-3 mt-4 btn-success">Choisir</button>
        </a>
      </div>
    </div>
  </div>
</div>
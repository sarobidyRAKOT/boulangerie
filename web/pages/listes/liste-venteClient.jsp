<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
    ArrayList<Vente_client> vente_clients = (ArrayList<Vente_client>) request.getAttribute("vente_clients");  
    int p = (int)  request.getAttribute("page");  
%>


<div class="row">

  <div class="col-md-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Rechercher par <b>CATEGORIE</b></h4>
        <form action="${pageContext.request.contextPath}/listeVenteClient" method="POST" class="forms-sample">
          <div class="form-group">
            <label for="form-control">Choisir date</label>
            <input type="date" name="daty" class="form-control form-control-lg" id="form-control" placeholder="Date vente">
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
                <th>id Vente</th>
                <th>id Client</th>
                <th>nom-tel client</th>  
                <th>Date Achat</th>  
                <th>prix Total</th>
              </tr>
            </thead>
            <tbody>
              <% for (Vente_client vente_client : vente_clients) { %>                
              <tr>
                <td> <%=vente_client.getId_vente()%> </td>
                <td> <%=vente_client.getId_client()%> </td>
                <td> <%=vente_client.getNom_tel()%> </td>
                <td> <%=vente_client.getDaty()%> </td>
                <td> <%=vente_client.getPrix_ttl()%> </td>
              </tr>
              <% } %>
            </tbody>
          </table>
          <div class="template-demo">
            <div class="btn-group mt-4" role="group" aria-label="Basic example">
                <a href="${pageContext.request.contextPath}/listeVenteClient?page=<%=(p-1)%>" class="btn btn-outline-secondary">precedent </a>
                <a href="" class="btn btn-outline-secondary"> ... </a>
                <a href="${pageContext.request.contextPath}/listeVenteClient?page=<%=(p+1)%>" class="btn btn-outline-secondary">suivant</a>
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
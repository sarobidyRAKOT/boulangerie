<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
    ArrayList<Vendeur> vendeurs = (ArrayList<Vendeur>) request.getAttribute("vendeurs");  
    ArrayList<Vendeur> vendeurs_commission = (ArrayList<Vendeur>) request.getAttribute("vendeurs_commission");  
    int p = (int)  request.getAttribute("page");  
%>


<div class="row">

  <div class="col-md-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Rechercher par <b>Periode</b></h4>
        <form action="${pageContext.request.contextPath}/listeCommission" method="POST" class="forms-sample">
          <div class="form-group">
            <label for="form-select">Choisir vendeur</label>
            <select name="id_vendeur" class="form-control form-control-lg" id="form-select">
              <% for (Vendeur v : vendeurs) { %>
              <option value="<%=v.getId()%>"><%=v.getNom()%></option>
              <% } %>
            </select>
          </div>
          <div class="form-group">
            <label for="form-control">Choisir date debut</label>
            <input type="date" name="daty_debut" class="form-control form-control-lg" id="form-control" placeholder="Date debut">
          </div>
          <div class="form-group">
            <label for="form-control">Choisir date fin</label>
            <input type="date" name="daty_fin" class="form-control form-control-lg" id="form-control" placeholder="Date debut">
          </div>
          <button type="submit" class="btn btn-lg btn-success mr-2">Rechercher</button>
        </form>
      </div>
    </div>
  </div>  

  <div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Listes des Commission par vendeur</h4>
        <p class="card-description">
          Listes Commission details
        </p>
        <div class="table-responsive pt-3">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>id vendeur</th>
                <th>nom</th>
                <th>commission ttl</th>  
                <th>prix_ttl</th>  
              </tr>
            </thead>
            <tbody>
              <% for (Vendeur vendeur : vendeurs_commission) { %>
              <tr>
                <td> <%=vendeur.getId()%> </td>
                <td> <%=vendeur.getNom()%> </td>
                <td> <%=vendeur.getCommissionTTL()%> </td>
                <td> <%=vendeur.getPrix_venteTTL()%> </td>
              </tr>
              <% } %>
            </tbody>
          </table>
          <div class="template-demo">
            <div class="btn-group mt-4" role="group" aria-label="Basic example">
                <a href="${pageContext.request.contextPath}/listeCommission?page=<%=(p-1)%>" class="btn btn-outline-secondary">precedent </a>
                <a href="" class="btn btn-outline-secondary"> ... </a>
                <a href="${pageContext.request.contextPath}/listeCommission?page=<%=(p+1)%>" class="btn btn-outline-secondary">suivant</a>
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
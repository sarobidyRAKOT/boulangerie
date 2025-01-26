<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  ArrayList<Vendeur> vendeurs = (ArrayList<Vendeur>) request.getAttribute("vendeurs");
%>


<div class="row">
  <div class="col-md-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h2 class="font-weight-bold mb-5 text-center">Entre une periode</h2>

        <form method="POST" action="${pageContext.request.contextPath}/etatCommission" class="forms-sample">
          <div id="produit-container">
            <div class="row produit-row">
              <div class="col-md-4">
                <div class="form-group row">
                  <label class="col-sm-4 col-form-label">Date debut</label>
                  <div class="col-sm-8">
                    <input type="date" name="date_debut" class="form-control" placeholder="Quantitee ingredient" required>
                  </div>
                </div>
              </div>
              <div class="col-md-4">
                <div class="form-group row">
                  <label class="col-sm-4 col-form-label">Date fin </label>
                  <div class="col-sm-8">
                    <input type="date" name="date_fin" class="form-control" placeholder="Quantitee ingredient" required>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <button type="submit" class="btn btn-lg btn-success mt-5 mr-2">Suivant</button>

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
                <th>Genre</th>
                <th>commission</th>
                <th>prix_ttl</th>  
                <th>nombre vente</th>
              </tr>
            </thead>
            <tbody>
              <% for (Vendeur vendeur : vendeurs) { %>
              <tr>
                <td> <%=vendeur.getGenre()%> </td>
                <td> <%=vendeur.getCommissionTTL()%> </td>
                <td> <%=vendeur.getPrix_venteTTL()%> </td>
                <td> <%=vendeur.getNbr_vente()%> </td>
              </tr>
              <% } %>
            </tbody>
          </table>
          <%-- <div class="template-demo">
            <div class="btn-group mt-4" role="group" aria-label="Basic example">
                <a href="${pageContext.request.contextPath}/listeCommission?page=<%=(p-1)%>" class="btn btn-outline-secondary">precedent </a>
                <a href="" class="btn btn-outline-secondary"> ... </a>
                <a href="${pageContext.request.contextPath}/listeCommission?page=<%=(p+1)%>" class="btn btn-outline-secondary">suivant</a>
            </div>
            <div class="d-flex align-items-center justify-content-center" style="">
              <p>page <%=p%></p>
            </div>
          </div> --%>
        </div>
      </div>
    </div>
  </div>
</div>



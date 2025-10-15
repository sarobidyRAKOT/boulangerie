<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
    ArrayList<Produit> produits = (ArrayList<Produit>) request.getAttribute("produits");  
    ArrayList<Prix_vente> prix_ventes = (ArrayList<Prix_vente>) request.getAttribute("prix_ventes");  
%>


<div class="row">

  <div class="col-md-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Rechercher par <b>Produit</b></h4>
        <form action="${pageContext.request.contextPath}/listeHistoriquePrixProduit" method="POST" class="forms-sample">

          <div id="produit-container">
            <div class="row produit-row">
              <div class="col-md-9 mt-1">
                <div class="form-group row">
                  <label class="col-sm-2 col-form-label">Choisir produit</label>
                  <div class="col-sm-10">
                    <select name="id_produit" class="form-control form-control-lg" id="form-select">
                      <option value="">Tous les produits</option>
                      <% for (Produit produit : produits) { %>
                      <option value="<%=produit.getId()%>"><%=produit.getNom()%></option>
                      <% } %>
                    </select>
                  </div>
                </div>
              </div>
              <div class="col-md-3">
                <div class="form-group row">
                  <button type="submit" class="btn btn-lg btn-success ml-5">Rechercher</button>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>  

  <div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Listes Historique prix de ventes</h4>
        <p class="card-description">
          Listes Historiques details
        </p>
        <div class="table-responsive pt-3">
          <table class="table table-bordered">
            <thead>
                <th>#</th>
                <th>dates</th>
                <th>produits</th>
                <th>Montants</th>  
                <th>Motifs</th>  
              </tr>
            </thead>
            <tbody>
              <% for (Prix_vente p : prix_ventes) { %>
              <tr>
                <td> <%=p.getId_prxivente()%></td>
                <td> <%=p.getDaty().toString()%></td>
                <td> <%=p.getNom_produit()%></td>
                <td> <%=p.getPrix()%></td>
                <td> <%=p.getMotif()%></td>
              </tr>
              <% } %>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>
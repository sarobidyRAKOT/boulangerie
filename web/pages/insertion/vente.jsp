<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  ArrayList<Produit> produits = (ArrayList<Produit>) request.getAttribute("produits");
  ArrayList<Client> clients = (ArrayList<Client>) request.getAttribute("clients");
  ArrayList<Vendeur> vendeurs = (ArrayList<Vendeur>) request.getAttribute("vendeurs");
%>


<div class="row">
  <div class="col-md-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h2 class="font-weight-bold mb-5 text-center">Insertion Vente et vente detail</h2>

        <form method="POST" action="${pageContext.request.contextPath}/insertion_vente" class="forms-sample">
          <div class="form-group row">
            <label class="col-sm-3 col-form-label">Choisier client</label>
            <div class="col-sm-9">
              <select name="id_client" class="form-control">
              <% for (Client client : clients) { %>
                <option value="<%=client.getId()%>"><%=client.getNom()%> - <%=client.getTel()%></option>
              <% } %>
              </select>
            </div>
          </div>
          <div class="form-group row">
            <label class="col-sm-3 col-form-label">Choisier Vendeur</label>
            <div class="col-sm-9">
              <select name="id_vendeur" class="form-control">
              <% for (Vendeur vendeur : vendeurs) { %>
                <option value="<%=vendeur.getId()%>"><%=vendeur.getNom()%></option>
              <% } %>
              </select>
            </div>
          </div>
          <div class="form-group row">
            <label for="form-input" class="col-sm-3 col-form-label">Date vente</label>
            <div class="col-sm-9">
              <input type="date" name="daty" class="form-control" id="form-input" placeholder="Date vente" required>
            </div>
          </div>
          <div id="produit-container">
            <div class="row produit-row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Produits</label>
                  <div class="col-sm-9">
                    <select name="id_produit[]" class="form-control">
                      <% for (Produit produit : produits) { %>
                      <option value="<%=produit.getId()%>"><%=produit.getNom()%></option>
                      <% } %>
                    </select>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-4 col-form-label">Quantite produit</label>
                  <div class="col-sm-8">
                    <input name="quantite_produit[]" class="form-control" placeholder="Quantitee ingredient" required>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <%-- <div class="row d-flex justify-content-cente"> --%>
            <div class="row d-flex">
              <button type="button" id="add-produit" class="btn btn-lg btn-info mx-3 ml-auto">Ajouter produit</button>
            </div>
            <button type="submit" class="btn btn-lg btn-success mt-5 mr-2">Suivant</button>
            <a href="${pageContext.request.contextPath}">
              <button type="button" class="btn btn-lg btn-secondary mt-5 mr-2">Retour</button>
            </a>
          <%-- </div> --%>

        </form>
      </div>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/js/nvProduit.js"></script>

<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.*" %> 
<%@page import="java.time.LocalDate" %> 

<%

  HashMap <String, String> mois = (HashMap <String, String>) request.getAttribute ("mois");
  LocalDate date = LocalDate.now();

          // System.out.println(mois.size());
  ArrayList <Produit> produits = (ArrayList<Produit>) request.getAttribute("produits");
%>

<div class="row">

  <div class="col-md-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Rechercher PRODUITS CONSEILEES</h4>
        <form action="${pageContext.request.contextPath}/listeProduit" method="POST" class="forms-sample">
          <div class="form-group">
            <label for="form-select">Choisir Mois</label>
            <select name="month" class="form-control form-control-lg" id="form-select">
              <option value="<%=date.getMonthValue()%>"><%=date.getMonth().toString()%></option>
              <% for (String cle : mois.keySet()) { %>
              <option value="<%=cle%>"><%=mois.get(cle)%></option>
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
        <h4 class="card-title">Listes Produits</h4>
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
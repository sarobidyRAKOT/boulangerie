<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) request.getAttribute("ingredients");
%>

<div class="row">
  <div class="col-md-12 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Formule de production</h4>
        <p class="card-description">
          Ajouter un autre formule de production
        </p>

        <form method="POST" action="${pageContext.request.contextPath}/insertion_formule_detail" class="forms-sample">

          <div id="ingredient-container">
            <div class="row ingredient-row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Ingredient</label>
                  <div class="col-sm-9">
                    <select name="ingredients[]" class="form-control">
                      <% for (Ingredient i : ingredients) { %>
                        <option value="<%= i.getId() %>"><%= i.getNom() %></option>
                      <% } %>
                    </select>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Quantite</label>
                  <div class="col-sm-9">
                    <input name="quantites[]" class="form-control" placeholder="Quantitee ingredient" required>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row">
            <button type="button" id="add-ingredient" class="btn btn-lg btn-info my-2 mx-3">Ajouter champ ingredient</button>
          </div>

          <button type="submit" class="btn btn-lg btn-success mr-2">Suivant</button>
          <a href="${pageContext.request.contextPath}">
            <button type="button" class="btn btn-lg btn-secondary mr-2">Retour</button>
          </a>
        </form>
      </div>
    </div>
  </div>
</div>


<script src="${pageContext.request.contextPath}/js/nouvelleChamp.js"></script>

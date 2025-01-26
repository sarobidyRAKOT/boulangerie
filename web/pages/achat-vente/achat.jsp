<div class="row">

            <div class="col-md-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Achats Ingredients</h4>
                  <p class="card-description">
                    Faire des achats Ingredients
                  </p>
                  <form class="forms-sample">
                    <div class="form-group">
                      <label for="form-select">Choisir ingredients</label>
                      <select class="form-control form-control-lg" id="form-select">
                        <option>ingredients 1</option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="form-select">Quantite <b>(par Pieces)</b></label>
                      <input type="number" class="form-control form-control-lg" id="form-select" placeholder="Quantite ingredients">
                    </div>
                    <div class="form-group">
                      <label for="form-input">Prix unitaire <b>(par Pieces)</b></label>
                      <input type="number" class="form-control form-control-lg" id="form-input" placeholder="Prix unitaire">
                    </div>

                    <button type="submit" class="btn btn-lg btn-success mr-2">Ajoute au panier</button>
                    <a href="${pageContext.request.contextPath}">
                      <button type="button" class="btn btn-lg btn-info mr-2">Retour</button>
                    </a>
                  </form>
                </div>
              </div>
            </div>
</div>
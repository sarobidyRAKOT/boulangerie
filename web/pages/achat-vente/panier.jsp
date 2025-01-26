<div class="row">
            <div class="col-lg-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Resume achat</h4>
                  <div class="table-responsive pt-3">
                    <table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>Date achat</th>
                          <th>Prix total</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>2024-01-03</td>
                          <td>200</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <a href="${pageContext.request.contextPath}">
                      <button type="button" class="btn btn-lg btn-success mt-4">Valider Achat</button>
                  </a>
                </div>
              </div>
            </div>

            <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Paniers</h4>
                  <p class="card-description">
                    Le contenu de votre panier, <code>2025-01-03</code> (listes des ingredients)
                  </p>
                  <div class="table-responsive pt-3">
                    <table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Nom ingredient</th>
                          <th>Quantite</th>
                          <th>Unite</th>
                          <th>Prix unitaire par unite</th>
                          <th>Personnaliser les quantites</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>1</td>
                          <td>Farine</td>
                          <td>200</td>
                          <td>KG</td>
                          <td>1000</td>
                          <td>
                            <a class="btn btn-sm btn-warning" href="">Retirer</a>
                            <a class="btn btn-sm btn-info" href="">Ajouter</a>
                            <a class="btn btn-sm btn-danger ml-4" href="">Retirer tous</a>
                          </td>
                        </tr>
                        <%-- ${pageContext.request.contextPath}/ --%>
                      </tbody>
                    </table>
                  </div>
                  <a href="${pageContext.request.contextPath}">
                      <button type="button" class="btn btn-lg btn-success mt-4">Valider Achat</button>
                  </a>
                </div>
              </div>
            </div>
</div>
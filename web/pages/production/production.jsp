<div class="row">
  <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">PRODUCTIONS</h4>
        <form action="${pageContext.request.contextPath}/insertionProduction" method="POST" class="forms-sample">
          <div class="form-group">
            <label>Quantite ingredient usuel en n fois</label>
            <input type="number" name="n_ingredient" class="form-control form-control-lg" placeholder="Quantite ingredient multiplie par ..." aria-label="Quantite ingredient multiplie par ...">
          </div>
          <div class="form-group">
            <label>Quantite produits produitent</label>
            <input type="number" name="quantite_produite" class="form-control form-control-lg" placeholder="Quantite produit produitent" aria-label="Quantite produit produitent">
          </div>
          <div class="form-group">
            <label>Date production</label>
            <input type="date" name="daty" class="form-control form-control-lg" placeholder="Date production" aria-label="Date production">
          </div>
          <button type="submit" class="btn btn-lg btn-success mr-2">Suivant</button>
          <a href="${pageContext.request.contextPath}/choisirProduit">
            <button type="button" class="btn btn-lg btn-info mr-2">Retour</button>
          </a>
          <a href="${pageContext.request.contextPath}/choisirProduit">
            <button type="button" class="btn btn-lg btn-danger mr-2">Annuler</button>
          </a>
        </form>
      </div>
    </div>
  </div>
</div>
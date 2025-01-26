<%
  String contenu = (String) request.getAttribute ("contenu");
%>

<div class="row">
  <div class="col-12 grid-margin stretch-card">
    <div class="card">
      <div class="row">
        <div class="col-md-12">
          <div class="card-body">
            <h4 class="card-title">Insertion basique</h4>
            <div class="template-demo">
              <a href="${pageContext.request.contextPath}/"><button type="button" class="btn btn-primary">Unite</button></a>
              <a href="${pageContext.request.contextPath}/categorie"><button type="button" class="btn btn-primary">Categorie</button></a>
              <a href="${pageContext.request.contextPath}/ingredient"><button type="button" class="btn btn-primary">Ingredient</button></a>
              <a href="${pageContext.request.contextPath}/produit"><button type="button" class="btn btn-primary">Produit</button></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="col-md-12 grid-margin stretch-card">
    <jsp:include page="<%=contenu%>"/>
  </div>
</div>
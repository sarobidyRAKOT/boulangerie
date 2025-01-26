<%@page import="mg.ITU.boulangerie.beans.*" %> 
<%@page import="java.util.ArrayList" %> 

<%
  ArrayList <Formule> formules = (ArrayList<Formule>) request.getAttribute("formules");
%>

<div class="row">
            <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Listes formules</h4>
                  <p class="card-description">
                    Listes des formules pour produire des <code>pain</code>
                  </p>
                  <div class="table-responsive pt-3">
                    <table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Description</th>
                          <th>Date</th>
                          <th>Quantite produite</th>
                          <th></th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                            
                        <% for (Formule formule : formules) { %>
                        <tr>
                          <td><%=formule.getId()%></td>
                          <td><%=formule.getDescription()%></td>
                          <td><%=formule.getDaty()%></td>
                          <td><%=formule.getQuantite_produite()%></td>
                          <td>
                            <a class="btn btn-sm btn-block btn-warning mr-3" href="${pageContext.request.contextPath}/detailFormule?id_formule=<%=formule.getId()%>">Details</a>
                          </td>
                          <td>
                            <a class="btn btn-sm btn-block btn-success" href="${pageContext.request.contextPath}/production?id_formule=<%=formule.getId()%>">Choisir</a>
                          </td>
                        </tr>
                        <% } %>
                      </tbody>
                    </table>
                  </div>

                  <a href="${pageContext.request.contextPath}/choisir_produit">
                    <button type="button" class="btn btn-lg mt-3 btn-info">Retour</button>
                  </a>
                </div>
              </div>
            </div>
</div>
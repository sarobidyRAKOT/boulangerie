document.addEventListener("DOMContentLoaded", function () {
    const addProduitButton = document.getElementById("add-produit");
    const produitContainer = document.getElementById("produit-container");

    addProduitButton.addEventListener("click", function () {
        // Vérifiez si les champs précédents sont remplis
        const lastRow = produitContainer.querySelector(".produit-row:last-child");
        const lastQuantite_produit = lastRow.querySelector("input[name='quantite_produit[]']").value;
        const lastPrix_unitaire = lastRow.querySelector("input[name='prix_unitaire[]']").value;

        if (!lastQuantite_produit || lastQuantite_produit.trim() === "" 
            || !lastPrix_unitaire || lastPrix_unitaire.trim() === "") {
            alert("Veuillez remplir la quantitee et le prix unitaire pour le dernier produit avant d'en ajouter un autre.");
            return;
        }

        // Clonez le dernier bloc de champs
        const newRow = lastRow.cloneNode(true);

        // Effacez les valeurs des champs du nouveau bloc
        const inputs = newRow.querySelectorAll("input, select");
        inputs.forEach(input => {
            if (input.tagName === "INPUT") {
                input.value = ""; // Effacez la valeur de l'input
            } else if (input.tagName === "SELECT") {
                input.selectedIndex = 0; // Réinitialisez le champ select
            }
        });

        // Ajoutez le nouveau bloc au conteneur
        produitContainer.appendChild(newRow);
    });
});
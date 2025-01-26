document.addEventListener("DOMContentLoaded", function () {
    const addIngredientButton = document.getElementById("add-ingredient");
    const ingredientContainer = document.getElementById("ingredient-container");

    addIngredientButton.addEventListener("click", function () {
        // Vérifiez si les champs précédents sont remplis
        const lastRow = ingredientContainer.querySelector(".ingredient-row:last-child");
        const lastQuantity = lastRow.querySelector("input[name='quantites[]']").value;

        if (!lastQuantity || lastQuantity.trim() === "") {
            alert("Veuillez remplir la quantitee pour le dernier ingredient avant d'en ajouter un autre.");
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
        ingredientContainer.appendChild(newRow);
    });
});
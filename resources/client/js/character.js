function pageLoad() {
    addAbilitiesTable();
    deleteRow();
}

function addAbilitiesTable(){
    $.ajax({
        url: '/abilities/list',
        type: 'GET',
        success: AbilitiesList => {

        let abilitiesHTML = `<div class="container">`
            + `<div class="row mb-2">`
            + `<div class="col-3 bg-light font-weight-bold">Track ID</div>`
            + `<div class="col-3 bg-light font-weight-bold">Year</div>`
            + `<div class="col-3 bg-light font-weight-bold">Artist</div>`
            + `<div class="col-3 bg-light font-weight-bold">Track</div>`
            + `</div>`;

    for (let track of trackList) trackHTML += `<div class="row col-form-label">`
        + `<div class="col-3">${track.trackID}</div>`
        + `<div class="col-3">${track.year}</div>`
        + `<div class="col-3">${track.artist}</div>`
        + `<div class="col-3">${track.title}</div>`
        + `</div>`;
    trackHTML += `</div>`;

    $('#abilityList').html(trackHTML);
}

});


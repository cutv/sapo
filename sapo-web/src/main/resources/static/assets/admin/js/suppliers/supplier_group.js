function doCreateGroupSupplier() {
    let newGroupSup = new createSupGroupParam();
    newGroupSup.id =  page.dialogs.elements.groupId.val();
    newGroupSup.title = page.dialogs.elements.groupTitleSup.val();
    newGroupSup.supplierCode =  page.dialogs.elements.groupCodeSup.val();
    newGroupSup.description =  page.dialogs.elements.groupDescriptionSup.val();
    console.log("trc ajax", newGroupSup)
    $.ajax({
        "headers": {
            "accept": "application/json",
            "content-type": "application/json"
        },
        "type": "POST",
        "url": origin +"/api/supplier_groups/create",
        "data": JSON.stringify(newGroupSup)
    })
        .done((data) => {
            App.IziToast.showSuccessAlert("Thêm nhóm nhà cung cấp thành công!");
            setTimeout(() => {
            }, 1500);

            page.dialogs.elements.showModalGroupSupplier.modal('hide');
            location.reload();

            // console.log("group",data);
            // alert("Thêm nhóm nhà cung cấp thành công")
            // // console.log("data", data);
            // let trSupplier = $("#tr_" + data.id);
            // let tr = renderRowGroupSupplier(data);
            // trSupplier.append(tr);
            // alert("Thêm nhóm nhà cung ứng thành công")

            // let renderGroup = $("#renderGroupLi");
            // getAllGroup(renderGroup);
            // $("#showModalGroupSupplier").modal('hide');

            // $.each(data, (i, item) => {
            //   let tr = renderRowGroupSupplier(item);
            //  $("#renderGroupLi").append(tr);
            // })
        })
        .fail((jqXHR) => {
            App.IziToast.showErrorAlert(App.AlertMessageVi.ERROR_400)
        })
}

function getAllGroup() {
    $.ajax({
        "headers": {
            "accept": "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: origin +"/api/supplier_groups",
    })
        .done((data) => {
            supplierGroups = data;
            $.each(data, function (index, value) {
                $("#renderGroupLi").append(
                    `<li onclick="handleChangeGroupSupp(${value.id})">
                        <p  class="w-20 border-0 " id="${value.id}" style="padding:7px; font-size: 12px;font-weight:450; width: 300px;border: unset;" value="${value.id}">
                        ${value.title}</p>
                        </li>`
                )
            });
        })
}

function handleChangeGroupSupp(supplierGroupId) {
    let supplierGroup = supplierGroups.find(supplierGroup => supplierGroup.id === supplierGroupId);
    page.elements.changeValueSup.attr("data-id",supplierGroup.id);

    page.elements.changeValueSup.val(supplierGroup.id);
    page.elements.changeValueSup.text(supplierGroup.title);

    var dropdown = new bootstrap.Dropdown(document.querySelector($("#changeValueSup")));
    dropdown.hide();
}

page.elements.changeValueSup.on('click', ()=> {
    var dropdown = new bootstrap.Dropdown(document.querySelector($("#changeValueSup")));
    dropdown.show();
})


function searchGroup() {

    let valueSearchInput = document.getElementById("inputKey").value
    console.log("valueSearchInput: ", valueSearchInput)
    console.log("supplierGroups :", supplierGroups)

    let groupSearch = supplierGroups.filter(group => {
        return group.title.toUpperCase().includes(valueSearchInput.toUpperCase());
    })
    console.log("nhom search", groupSearch)

    $("#renderGroupLi").empty();
    $.each(groupSearch, (i, item) => {
        $("#renderGroupLi").prepend (
            `<li onclick="handleChangeGroupSupp(${item.id})">
                    <p  class="w-20 border-0 " id="${item.id}" style="padding:7px; font-size: 12px; font-weight: 500; width: 300px;border: unset;" value="${item.id}">
                    ${item.title}</p>
                    </li>`
        )
    });
}

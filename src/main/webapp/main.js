function generateTable(json,cols,container,cm,entity){
    container.innerHTML = "";
    let table = document.createElement("table");
    table.className = "table table-striped marginTopTabla";
    let thead = document.createElement("thead");
    thead.className = "thead-dark";
    let tbody = document.createElement("tbody");
    generateTableHead(cols,table,thead);
    generateTableRows(json,tbody,table,cm,entity);
    container.appendChild(table);
}
function generateTableHead(cols,table,thead){
    let tr = document.createElement("tr");
    cols.forEach(element => {
        let th = document.createElement("th");
        th.setAttribute("scope","col");
        th.append(element);
        tr.appendChild(th);
    });
    thead.appendChild(tr);
    table.appendChild(thead);
}
function generateTableRows(json,body,table,cm,entity){
    json.forEach(element => {
        let tr = document.createElement("tr");
        for (let key in element) {
            let td = document.createElement("td");
            td.append(element[key]);
            tr.appendChild(td);
        }
        generateImagesForUpdateAndDelete(tr,element,cm,entity);
        body.appendChild(tr);
    });
    table.appendChild(body);
}
function generateImagesForUpdateAndDelete(tr,json,cm,entity){
    let tdUpdate = document.createElement("td");
    let imgUpdate = document.createElement("img");
    imgUpdate.setAttribute("src",updateImgPath);
    let res = "";
    for (let key in json) {
        res += json[key] + ",";
    }
    let fullIntel = res;
    imgUpdate.setAttribute("intel",fullIntel);
    imgUpdate.setAttribute("data-target",cm.getModal);
    imgUpdate.setAttribute("data-toggle","modal");
    imgUpdate.addEventListener("click", putValuesOnUpdateInput(imgUpdate,cm));
    tdUpdate.append(imgUpdate);
    tr.appendChild(tdUpdate);

    let tdDelete = document.createElement("td");
    let imgDelete = document.createElement("img");
    imgDelete.setAttribute("src",deleteImgPath);
    imgDelete.setAttribute("intel",json.id);
    imgDelete.addEventListener("click", hideRow(imgDelete,tr,entity));
    tdDelete.append(imgDelete);
    tr.appendChild(tdDelete);
}
function putValuesOnUpdateInput(img,cm){
    return () => {
        let intel = img.getAttribute("intel");
        let str = intel.split(",");
        for (let i = 0; i < cm.getInputs.length; i++) {
            cm.getInputs[i].value = str[i];
        }
        turnOnUpdateSwitch(cm.getCustomSwitch);
    };
    // cuando pones el switch en off te vacia los inputs
}
function hideRow(img,tr,entity){
    return () => {
        tr.className = "d-none";
        let intel = img.getAttribute("intel");
        // deleteRow(intel,entity);
    }
}
function deleteRow(intel,entity){
    let url = entity.getUrl + entity.getDelete + intel;
    fetchWithMethod(url,"DELETE");
}
function turnOnUpdateSwitch(cs){
    cs.getSwitcher.checked = true;
    cs.getInput.disabled = false;
    cs.getHeadline.innerHTML = cs.getTitleTrue;
}
function turnOffUpdateSwitch(cs){
    cs.getSwitcher.checked = false;
    cs.getInput.disabled = true;
    cs.getHeadline.innerHTML = cs.getTitleFalse;
}
function changeBetweenPostAndPut(cs){
    return () => {
        if(cs.getSwitcher.checked == true){
            turnOnUpdateSwitch(cs);
        }else{
            turnOffUpdateSwitch(cs);
        }
    }
}
class customSwitch{
    constructor(switcher,input,headline,titleTrue,titleFalse){
        this.switcher = switcher;
        this.input = input;
        this.headline = headline;
        this.titleTrue = titleTrue;
        this.titleFalse = titleFalse;
    }
    get getSwitcher(){
        return this.switcher;
    }
    get getInput(){
        return this.input;
    }
    get getHeadline(){
        return this.headline;
    }
    get getTitleTrue(){
        return this.titleTrue;
    }
    get getTitleFalse(){
        return this.titleFalse;
    }
}
class customModal{
    constructor(modal,customSwitch){
        this.modal = modal;
        this.inputs = [];
        this.customSwitch = customSwitch;
    }
    get getModal(){
        return this.modal;
    }
    set addInput(input){
        this.inputs.push(input);
    }
    get getInputs(){
        return this.inputs;
    }
    get getCustomSwitch(){
        return this.customSwitch;
    }
}
class EntityController{
    constructor(url){
        this.url = defaultUrl + url;
        this.delete = "/delete/?id=";
        this.save = "/save";
        this.update = "/update";
        this.get = "/getAll";
    }
    get getUrl(){
        return this.url;
    }
    get getDelete(){
        return this.delete;
    }
    get getSave(){
        return this.save;
    }
    get getUpdate(){
        return this.update;
    }
    get getAll(){
        return this.get;
    }
}
function saveCliente(entity){
    let nombre = inputNombreCliente.value;
    let body = {
        "nombre": nombre
    };
    let url = entity.getUrl + entity.getSave;
    fetchWithMethodAndBody(url,"POST",body);
}
function updateCliente(entity){
    let id = inputIdCliente.value;
    let nombre = inputNombreCliente.value;
    let body = {
        "id": id,
        "nombre": nombre
    };
    let url = entity.getUrl + entity.getUpdate;
    fetchWithMethodAndBody(url,"PUT",body);
}
function saveProducto(entity){
    let nombre = inputNombreProducto.value;
    let precio = inputPrecioProducto.value;
    let body = {
        "nombre": nombre,
        "precio": precio
    };
    let url = entity.getUrl + entity.getSave;
    fetchWithMethodAndBody(url,"POST",body);
}
function updateProducto(entity){
    let id = inputIdProducto.value;
    let nombre = inputNombreProducto.value;
    let precio = inputPrecioProducto.value;
    let body = {
        "id": id,
        "nombre": nombre,
        "precio": precio
    };
    let url = entity.getUrl + entity.getUpdate;
    fetchWithMethodAndBody(url,"PUT",body);
}
function saveFactura(entity){
    let cliente = inputClienteFactura.value;
    let fecha = inputFechaFactura.value;
    let monto = inputMontoFactura.value;
    let body = {
        "cliente": cliente,
        "fecha": fecha,
        "monto": monto
    };
    let url = entity.getUrl + entity.getSave;
    fetchWithMethodAndBody(url,"POST",body);
}
function updateFactura(entity){
    let cliente = inputClienteFactura.value;
    let fecha = inputFechaFactura.value;
    let monto = inputMontoFactura.value;
    let id = inputIdFactura.value;
    let body = {
        "id": id,
        "cliente": cliente,
        "fecha": fecha,
        "monto": monto
    };
    let url = entity.getUrl + entity.getUpdate;
    fetchWithMethodAndBody(url,"PUT",body);
}
function fetchJsonIntoTable(url,cols,container,cm,entity){
    // fetch(url)
    //     .then(r => r.json())
    //     .then(json => {
    //     generateTable(json,cols,container,cm);
    // })
    generateTable(url,cols,container,cm,entity);
}
function fetchJsonIntoReport(url,container){
    fetch(url)
        .then(r => r.json())
        .then(json => {
        generateTable(json,container);        
    })
}
function fetchWithMethodAndBody(url,method,jsonBody){
    fetch(url, {
        method: method,
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(jsonBody)
    });
}
function fetchWithMethod(url,method){
    fetch(url, {
        method: method,
        headers: {
            "Content-type": "application/json"
        }
    });
}

let arrayCliente = [
    {
        "id": 1,
        "nombre": "Julian"
    },
    {
        "id": 2,
        "nombre": "Batman"
    },
    {
        "id": 3,
        "nombre": "Kratos"
    },
    {
        "id": 4,
        "nombre": "Matrix"
    }
];
let arrayProducto = [
    {
        "id": 1,
        "nombre": "Pepas",
        "precio": 30
    },
    {
        "id": 2,
        "nombre": "Naranja",
        "precio": 50
    },
    {
        "id": 3,
        "nombre": "Sandia",
        "precio": 14
    },
    {
        "id": 4,
        "nombre": "Pera",
        "precio": 99
    }
];
let arrayFactura = [
    {
        "id": 1,
        "cliente": 1,
        "fecha": "2020-05-10",
        "monto": 30
    },
    {
        "id": 2,
        "cliente": 2,
        "fecha": "2020-04-11",
        "monto": 35
    },
    {
        "id": 3,
        "cliente": 3,
        "fecha": "2020-07-28",
        "monto": 40
    },
    {
        "id": 4,
        "cliente": 4,
        "fecha": "2020-02-11",
        "monto": 66
    }
];
let arrayStock = [
    {
        "id": 1,
        "producto": 1,
        "cantidad": 30
    },
    {
        "id": 2,
        "producto": 2,
        "monto": 35
    },
    {
        "id": 3,
        "producto": 3,
        "monto": 40
    },
    {
        "id": 4,
        "producto": 4,
        "monto": 66
    }
];

// cols for thead of abms
let clienteCols = [];
clienteCols.push("ID");
clienteCols.push("Nombre");
clienteCols.push("Modificación");
clienteCols.push("Baja");

let productoCols = [];
productoCols.push("ID");
productoCols.push("Nombre");
productoCols.push("Precio");
productoCols.push("Modificación");
productoCols.push("Baja");

let facturaCols = [];
facturaCols.push("ID");
facturaCols.push("Cliente");
facturaCols.push("Fecha");
facturaCols.push("Monto");
facturaCols.push("Modificación");
facturaCols.push("Baja");

let stockCols = [];
stockCols.push("ID");
stockCols.push("Producto");
stockCols.push("Cantidad");
stockCols.push("Modificación");
stockCols.push("Baja");

// containers
let abmClienteContainer = document.querySelector("#abmClienteContainer");
let abmProductoContainer = document.querySelector("#abmProductoContainer");
let abmFacturaContainer = document.querySelector("#abmFacturaContainer");
let abmStockContainer = document.querySelector("#abmControlStockContainer");

// images paths
let updateImgPath = "images/edit.png";
let deleteImgPath = "images/trash.png";

// switch togglers
let switcherCliente = document.querySelector("#customSwitchCliente");
let switcherProducto = document.querySelector("#customSwitchProducto");
let switcherFactura = document.querySelector("#customSwitchFactura");
let switcherStock = document.querySelector("#customSwitchControlStock");

// modal headlines
let headlineCliente = document.querySelector("#headlineCliente");
let headlineProducto = document.querySelector("#headlineProducto");
let headlineFactura = document.querySelector("#headlineFactura");
let headlineStock = document.querySelector("#headlineControlStock");

// disabled inputs
let inputCliente = document.querySelector("#inputIdCliente");
let inputProducto = document.querySelector("#inputIdProducto");
let inputFactura = document.querySelector("#inputIdFactura");
let inputStock = document.querySelector("#inputIdControlStock");

// instances of switcher class
let clienteSwitch = new customSwitch(switcherCliente,inputCliente,headlineCliente,"Modificar Cliente","Agregar Cliente");
let productoSwitch = new customSwitch(switcherProducto,inputProducto,headlineProducto,"Modificar Producto","Agregar Producto");
let facturaSwitch = new customSwitch(switcherFactura,inputFactura,headlineFactura,"Modificar Factura","Agregar Factura");
let stockSwitch = new customSwitch(switcherStock,inputStock,headlineStock,"Modificar Stock","Agregar Stock");

clienteSwitch.getSwitcher.addEventListener("click",changeBetweenPostAndPut(clienteSwitch));
productoSwitch.getSwitcher.addEventListener("click",changeBetweenPostAndPut(productoSwitch));
facturaSwitch.getSwitcher.addEventListener("click",changeBetweenPostAndPut(facturaSwitch));
stockSwitch.getSwitcher.addEventListener("click",changeBetweenPostAndPut(stockSwitch));

// modals ids
let clienteABMModal = "#clienteABMmodal";
let prodcutoABMModal = "#productoABMmodal";
let facturaABMModal = "#facturaABMmodal";
let stockABMModal = "#controlStockABMmodal";

// cliente's modal inputs 
let inputIdCliente = document.querySelector("#inputIdCliente");
let inputNombreCliente = document.querySelector("#inputNombreCliente");

// producto's modal inputs
let inputIdProducto = document.querySelector("#inputIdProducto");
let inputNombreProducto = document.querySelector("#inputNombreProducto");
let inputPrecioProducto = document.querySelector("#inputPrecioProducto");

// factura's modal inputs
let inputIdFactura = document.querySelector("#inputIdFactura");
let inputClienteFactura = document.querySelector("#inputClienteFactura");
let inputFechaFactura = document.querySelector("#inputFechaFactura");
let inputMontoFactura = document.querySelector("#inputMontoFactura");

// controlStock's modal inputs
let inputProductoStock = document.querySelector("#inputProductoControlStock");
let inputCantidadStock = document.querySelector("#inputCantidadControlStock");
let inputIdStock = document.querySelector("#inputIdControlStock");

let clienteCustomModal = new customModal(clienteABMModal,clienteSwitch);
clienteCustomModal.addInput = inputIdCliente;
clienteCustomModal.addInput = inputNombreCliente;

let productoCustomModal = new customModal(prodcutoABMModal,productoSwitch);
productoCustomModal.addInput = inputIdProducto;
productoCustomModal.addInput = inputNombreProducto;
productoCustomModal.addInput = inputPrecioProducto;

let facturaCustomModal = new customModal(facturaABMModal,facturaSwitch);
facturaCustomModal.addInput = inputIdFactura;
facturaCustomModal.addInput = inputClienteFactura;
facturaCustomModal.addInput = inputFechaFactura;
facturaCustomModal.addInput = inputMontoFactura;

let stockCustomModal = new customModal(stockABMModal,stockSwitch);
stockCustomModal.addInput = inputProductoStock;
stockCustomModal.addInput = inputCantidadStock;
stockCustomModal.addInput = inputIdStock;

const defaultUrl = "http://localhost:8080/";

let ClienteController = new EntityController("clientes");
let ProductoController = new EntityController("productos");
let FacturaController = new EntityController("facturas");
let StockController = new EntityController("controlStock");

// launch abms
document.querySelector(".cliente").addEventListener("click",() =>{
    let url = ClienteController.getUrl + ClienteController.getAll;
    fetchJsonIntoTable(arrayCliente,clienteCols,abmClienteContainer,clienteCustomModal,ClienteController);
});
document.querySelector(".producto").addEventListener("click",() =>{
    let url = ProductoController.getUrl + ProductoController.getAll;
    fetchJsonIntoTable(arrayProducto,productoCols,abmProductoContainer,productoCustomModal,ProductoController);
});
document.querySelector(".factura").addEventListener("click",() =>{
    let url = FacturaController.getUrl + FacturaController.getAll;
    fetchJsonIntoTable(arrayFactura,facturaCols,abmFacturaContainer,facturaCustomModal,FacturaController);
});
document.querySelector(".stock").addEventListener("click",() =>{
    let url = StockController.getUrl + StockController.getAll;
    fetchJsonIntoTable(arrayStock,stockCols,abmStockContainer,stockCustomModal,StockController);
});

function readyy(){
    console.log("breo");
    let url = ClienteController.getUrl + ClienteController.getAll;
    // generateTable(array,clienteCols,abmClienteContainer,clienteCustomModal,ClienteController)
    fetchJsonIntoTable(arrayCliente,clienteCols,abmClienteContainer,clienteCustomModal,ClienteController);
}
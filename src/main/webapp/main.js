const defaultUrl = "http://localhost:8080/";
function generateTable(json,cols,container,cm,entity){
    container.innerHTML = "";
    let table = document.createElement("table");
    table.className = "table table-striped marginTopTabla";
    let thead = document.createElement("thead");
    thead.className = "thead-dark";
    let tbody = document.createElement("tbody");
    generateTableHead(cols,table,thead);
    // generateTableRows(json,tbody,table,cm,entity);
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
        if(cm != null){
            generateImageForUpdate(tr,element,cm);
            generateImageForDelete(tr,element,entity);
        }
        body.appendChild(tr);
    });
    table.appendChild(body);
}
function generateImageForUpdate(tr,json,cm){
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
}
function generateImageForDelete(tr,id,entity){
    let tdDelete = document.createElement("td");
    let imgDelete = document.createElement("img");
    imgDelete.setAttribute("src",deleteImgPath);
    console.log("id en generateImageForDelete: "+id);
    imgDelete.setAttribute("intel",id);
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
}
function hideRow(img,tr,entity){
    return () => {
        tr.className = "d-none";
        let intel = img.getAttribute("intel");
        deleteRow(intel,entity);
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
    constructor(url,name){
        this.url = defaultUrl + url;
        this.delete = "/delete?id" + this.name + "=";
        this.save = "/save";
        this.update = "/update";
        this.get = "/getAll";
        this.name = name;
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
    get getName(){
        return this.name;
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
function saveStock(entity){
    let producto = inputProductoStock.value;
    let cantidad = inputCantidadStock.value;
    let body = {
        "producto": producto,
        "cantidad": cantidad,
    };
    let url = entity.getUrl + entity.getSave;
    fetchWithMethodAndBody(url,"POST",body);
}
function updateStock(entity){
    let producto = inputProductoStock.value;
    let cantidad = inputCantidadStock.value;
    let id = inputIdStock.value;
    let body = {
        "id": id,
        "producto": producto,
        "cantidad": cantidad
    };
    let url = entity.getUrl + entity.getUpdate;
    fetchWithMethodAndBody(url,"PUT",body);
}
function fetchJsonIntoTable(url,cols,container,cm,entity){
    fetch(url)
        .then(r => r.json())
        .then(json => {
        generateTable(json,cols,container,cm,entity);
    })
}
function fetchWithMethodAndBody(url,meth,jsonBody){
    fetch(url, {
        method: meth,
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(jsonBody)
    });
}
function fetchWithMethod(url,meth){
    fetch(url, {
        method: meth,
        headers: {
            "Content-type": "application/json"
        }
    });
}
function submitCliente(){
    if(switcherCliente.checked == true){
        updateCliente(ClienteController);
    }else{
        saveCliente(ClienteController);
    }
}
function submitProducto(){
    if(switcherProducto.checked == true){
        updateProducto(ProductoController);
    }else{
        saveProducto(ProductoController);
    }
}
function submitFactura(){
    if(switcherFactura.checked == true){
        updateFactura(FacturaController);
    }else{
        saveFactura(FacturaController);
    }
}
function submitStock(){
    if(switcherStock.checked == true){
        updateStock(StockController);
    }else{
        saveStock(StockController);
    }
}
function buscarVentas(){
    let fecha = document.querySelector("#inputFechaVentas").value;
    if(fecha == ""){
        buscarTodasLasVentas();
    }else{
        buscarVentasConFecha(fecha);
    }
}
function buscarTodasLasVentas(){
    let url = defaultUrl + "facturas/getAll"; //TODO
    fetch(url)
        .then(r => r.json())
        .then(json => {
        generarFacturaCRUD(json,colsReporteVentas,reporteVentasContainer,null,null);
    })
}
function buscarVentasConFecha(fecha){
    let url = defaultUrl + "facturas/getByFecha?date=" + fecha; //TODO en espera de enpoint definitivo
    fetch(url)
        .then(r => r.json())
        .then(json => {
        generarFacturaCRUD(json,colsReporteVentas,reporteVentasContainer,null,null);
    })
}

// cols for theads
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
facturaCols.push("Fecha");
facturaCols.push("Monto");
facturaCols.push("Modificación");
facturaCols.push("Baja");

let stockCols = [];
stockCols.push("Producto");
stockCols.push("Cantidad");
stockCols.push("Modificación");
stockCols.push("Baja");

let colsReporteClientes = [];
colsReporteClientes.push("ID");
colsReporteClientes.push("Nombre");
colsReporteClientes.push("Total");

let colsReporteVentas = [];
colsReporteVentas.push("ID");
colsReporteVentas.push("Fecha");
colsReporteVentas.push("Monto");

let colsHistorico = [];
colsHistorico.push("ID");
colsHistorico.push("Nombre");
colsHistorico.push("Precio");

// containers
let abmClienteContainer = document.querySelector("#abmClienteContainer");
let abmProductoContainer = document.querySelector("#abmProductoContainer");
let abmFacturaContainer = document.querySelector("#abmFacturaContainer");
let abmStockContainer = document.querySelector("#abmControlStockContainer");
let reporteClientesContainer = document.querySelector("#reporteClientesContainer");
let reporteVentasContainer = document.querySelector("#reporteVentasContainer");
let historicoContainer = document.querySelector("#historicoContainer");

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
let productoABMModal = "#productoABMmodal";
let facturaABMModal = "#facturaABMmodal";
let stockABMModal = "#controlStockABMmodal";

// cliente's modal inputs 
let inputIdCliente = inputCliente;
let inputNombreCliente = document.querySelector("#inputNombreCliente");

// producto's modal inputs
let inputIdProducto = inputProducto;
let inputNombreProducto = document.querySelector("#inputNombreProducto");
let inputPrecioProducto = document.querySelector("#inputPrecioProducto");

// factura's modal inputs
let inputIdFactura = inputFactura;
let inputClienteFactura = document.querySelector("#inputClienteFactura");
let inputFechaFactura = document.querySelector("#inputFechaFactura");
let inputMontoFactura = document.querySelector("#inputMontoFactura");

// controlStock's modal inputs
let inputIdStock = inputStock;
let inputProductoStock = document.querySelector("#inputProductoControlStock");
let inputCantidadStock = document.querySelector("#inputCantidadControlStock");

let clienteCustomModal = new customModal(clienteABMModal,clienteSwitch);
clienteCustomModal.addInput = inputIdCliente;
clienteCustomModal.addInput = inputNombreCliente;

let productoCustomModal = new customModal(productoABMModal,productoSwitch);
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

let ClienteController = new EntityController("clientes","Cliente");
let ProductoController = new EntityController("productos","Producto");
let FacturaController = new EntityController("facturas","Factura");
let StockController = new EntityController("controlStock","Stock");

// launch abms and reports
document.querySelector(".cliente").addEventListener("click",() =>{
    let url = ClienteController.getUrl + ClienteController.getAll;
    fetch(url)
        .then(r => r.json())
        .then(json => {
        generarClienteCRUD(json,clienteCols,abmClienteContainer,clienteCustomModal,ClienteController);
    })
    // fetchJsonIntoTable(url,clienteCols,abmClienteContainer,clienteCustomModal,ClienteController);
});
document.querySelector(".producto").addEventListener("click",() =>{
    let url = ProductoController.getUrl + ProductoController.getAll;
    fetch(url)
        .then(r => r.json())
        .then(json => {
        generarProductoCRUD(json,productoCols,abmProductoContainer,productoCustomModal,ProductoController);
    })
    // fetchJsonIntoTable(url,productoCols,abmProductoContainer,productoCustomModal,ProductoController);
});
document.querySelector(".factura").addEventListener("click",() =>{
    let url = FacturaController.getUrl + FacturaController.getAll;
    fetch(url)
        .then(r => r.json())
        .then(json => {
        generarFacturaCRUD(json,facturaCols,abmFacturaContainer,facturaCustomModal,FacturaController);
    })
    // fetchJsonIntoTable(url,facturaCols,abmFacturaContainer,facturaCustomModal,FacturaController);
});
document.querySelector(".stock").addEventListener("click",() =>{
    let url = StockController.getUrl + StockController.getAll;
    fetch(url)
        .then(r => r.json())
        .then(json => {
        generarStockCRUD(json,stockCols,abmStockContainer,stockCustomModal,StockController);
    })
    // fetchJsonIntoTable(url,stockCols,abmStockContainer,stockCustomModal,StockController);
});
document.querySelector(".reporteClientes").addEventListener("click",() =>{
    let url = defaultUrl + "reporteClientes";
    fetch(url)
        .then(r => r.json())
        .then(json => {
        generarClienteCRUD(json,colsReporteClientes,reporteClientesContainer,null,null);
    })
    // fetchJsonIntoTable(url,colsReporteClientes,reporteClientesContainer,null,null);
});
document.querySelector(".historico").addEventListener("click",() =>{
    let url = defaultUrl + "productoHistorico";
    fetch(url)
        .then(r => r.json())
        .then(json => {
        generarTablaHistorico(json,colsHistorico,historicoContainer);
    })
    // fetchJsonIntoTable(url,colsHistorico,historicoContainer,null,null);
});
document.querySelector(".reporteVentas").addEventListener("click",() =>{
    let url = defaultUrl + "reporteVentas";
    fetch(url)
        .then(r => r.json())
        .then(json => {
        generarFacturaCRUD(json,colsHistorico,historicoContainer,null,null);
    })
    // fetchJsonIntoTable(url,colsHistorico,historicoContainer,null,null);
});

// submit modals buttons
document.querySelector("#submitCliente").addEventListener("click",submitCliente);
document.querySelector("#submitProducto").addEventListener("click",submitProducto);
document.querySelector("#submitFactura").addEventListener("click",submitFactura);
document.querySelector("#submitControlStock").addEventListener("click",submitStock);
document.querySelector("#buscarVentasPorFecha").addEventListener("click",buscarVentas);

function readyy(){
    console.log("document ready");
    let url = ClienteController.getUrl + ClienteController.getAll;
    generarClienteCRUD(url,clienteCols,abmClienteContainer,clienteCustomModal,ClienteController);
}

function generarFacturaCRUD(json,cols,container,cm,entity){
    container.innerHTML = "";
    let table = document.createElement("table");
    table.className = "table table-striped marginTopTabla";
    let thead = document.createElement("thead");
    thead.className = "thead-dark";
    let tbody = document.createElement("tbody");
    generateTableHead(cols,table,thead);
    generarRowsFacturaCRUD(json,tbody,table,cm,entity);
    container.appendChild(table);   
}
function generarRowsFacturaCRUD(json,body,table,cm,entity){
    json.forEach(element => {
        let tr = document.createElement("tr");
        let tdId = document.createElement("td");
        let tdFecha = document.createElement("td");
        let tdMonto = document.createElement("td");
        tdId.append(element.idFactura);
        tdFecha.append(element.fecha);
        tdMonto.append(element.monto);
        tr.appendChild(tdId);
        tr.appendChild(tdFecha);
        tr.appendChild(tdMonto);
        if(cm != null){
            generateImageForUpdate(tr,element,cm);
            generateImageForDelete(tr,element.idFactura,entity);
        }
        body.appendChild(tr);
    });
    table.appendChild(body);
}
function generarClienteCRUD(json,cols,container,cm,entity){
    container.innerHTML = "";
    let table = document.createElement("table");
    table.className = "table table-striped marginTopTabla";
    let thead = document.createElement("thead");
    thead.className = "thead-dark";
    let tbody = document.createElement("tbody");
    generateTableHead(cols,table,thead);
    generarRowsClienteCRUD(json,tbody,table,cm,entity);
    container.appendChild(table);   
}
function generarRowsClienteCRUD(json,body,table,cm,entity){
    json.forEach(element => {
        let tr = document.createElement("tr");
        let tdId = document.createElement("td");
        let tdNombre = document.createElement("td");
        tdId.append(element.idCliente);
        tdNombre.append(element.nombre);
        tr.appendChild(tdId);
        tr.appendChild(tdNombre);
        if(cm != null){
            generateImageForUpdate(tr,element,cm);
            generateImageForDelete(tr,element.idCliente,entity);
        }
        body.appendChild(tr);
    });
    table.appendChild(body);
}
function generarStockCRUD(json,cols,container,cm,entity){
    container.innerHTML = "";
    let table = document.createElement("table");
    table.className = "table table-striped marginTopTabla";
    let thead = document.createElement("thead");
    thead.className = "thead-dark";
    let tbody = document.createElement("tbody");
    generateTableHead(cols,table,thead);
    generarRowsStockCRUD(json,tbody,table,cm,entity);
    container.appendChild(table);   
}
function generarRowsStockCRUD(json,body,table,cm,entity){
    json.forEach(element => {
        let tr = document.createElement("tr");
        let tdId = document.createElement("td");
        let tdCantidad = document.createElement("td");
        tdId.append(element.productoIdWrapper.idProducto);
        tdCantidad.append(element.cantidadStock);
        tr.appendChild(tdId);
        tr.appendChild(tdCantidad);
        if(cm != null){
            generateImageForUpdate(tr,element,cm);
            generateImageForDelete(tr,element.productoIdWrapper.idProducto,entity);
        }
        body.appendChild(tr);
    });
    table.appendChild(body);
}
function generarProductoCRUD(json,cols,container,cm,entity){
    container.innerHTML = "";
    let table = document.createElement("table");
    table.className = "table table-striped marginTopTabla";
    let thead = document.createElement("thead");
    thead.className = "thead-dark";
    let tbody = document.createElement("tbody");
    generateTableHead(cols,table,thead);
    generarRowsProductoCRUD(json,tbody,table,cm,entity);
    container.appendChild(table);   
}
function generarRowsProductoCRUD(json,body,table,cm,entity){
    json.forEach(element => {
        let tr = document.createElement("tr");
        let tdId = document.createElement("td");
        let tdNombre = document.createElement("td");
        let tdPrecio = document.createElement("td");
        tdId.append(element.idProducto);
        tdNombre.append(element.nombre);
        tdPrecio.append(element.precio);
        tr.appendChild(tdId);
        tr.appendChild(tdNombre);
        tr.appendChild(tdPrecio);
        if(cm != null){
            generateImageForUpdate(tr,element,cm);
            generateImageForDelete(tr,element.idProducto,entity);
        }
        body.appendChild(tr);
    });
    table.appendChild(body);
}
function generarTablaHistorico(json,cols,container){
    container.innerHTML = "";
    let table = document.createElement("table");
    table.className = "table table-striped marginTopTabla";
    let thead = document.createElement("thead");
    thead.className = "thead-dark";
    let tbody = document.createElement("tbody");
    generateTableHead(cols,table,thead);
    generarRowHistorico(json,tbody,table);
    container.appendChild(table);      
}
function generarRowHistorico(json,body,table){
    let tr = document.createElement("tr");
    let tdId = document.createElement("td");
    let tdNombre = document.createElement("td");
    let tdPrecio = document.createElement("td");
    tdId.append(json.idProducto);
    tdNombre.append(json.nombre);
    tdPrecio.append(json.precio);
    tr.appendChild(tdId);
    tr.appendChild(tdNombre);
    tr.appendChild(tdPrecio);
    body.appendChild(tr);
    table.appendChild(body);
}
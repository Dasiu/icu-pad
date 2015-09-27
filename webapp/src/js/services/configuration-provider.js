angular.module('ICUPad.services.Configuration', [])

    .service('configuration', [function () {
        this._serverProtocol = "https";
        this._serverAddress = "192.168.0.11";
        this._serverPort = "8443";

        this._formServerProtocol = "http";
        this._formServerAddress = "192.168.0.11";
        this._formServerPort = "8070";

        this.server = function () {
            return this._serverProtocol + "://" + this._serverAddress + ":" + this._serverPort;
        };
        this.formServer = function () {
            return this._formServerProtocol + "://" + this._formServerAddress + ":" + this._formServerPort;
        };
    }]);

angular.module('ICUPad.services.Configuration', [])

    .service('configuration', [function () {
        this._serverProtocol = window.localStorage.getItem('_serverProtocol');
        this._serverAddress = window.localStorage.getItem('_serverAddress');
        this._serverPort = window.localStorage.getItem('_serverPort');

        this._formServerProtocol = "http";
        this._formServerAddress = "192.168.0.11";
        this._formServerPort = "8070";

        this.server = function () {
            return this._serverProtocol + "://" + this._serverAddress + ":" + this._serverPort;
        };
        this.formServer = function () {
            return this._formServerProtocol + "://" + this._formServerAddress + ":" + this._formServerPort;
        };

        this.saveConfiguration = function () {
            window.localStorage.setItem('_serverProtocol', this._serverProtocol);
            window.localStorage.setItem('_serverAddress', this._serverAddress);
            window.localStorage.setItem('_serverPort', this._serverPort);
        };

    }]);

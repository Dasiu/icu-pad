angular.module('ICUPad.services.Conversion', [])

.service('conversionService', [ 'configuration', '$http', function (configuration, $http) {

    return {
        convert: function(form) {

            function findFieldByDomainName(domainName, fields) {
                for (var i = 0; i < fields.length; i++) {
                    if (fields[i].field_domain == domainName) {
                        return fields[i];
                    }
                }
                return null;
            }

            function findOptionValueById(id, options) {
                var result =
                    options.filter(function (option) {
                        return option.option_id == id;
                    }).map(function (option) {
                        return option.option_title;
                    });
                if (result && result[0]) {
                    return result[0];
                }
            }

            function extractFieldValue(field) {
                switch (field.field_type) {
                    case 'textfield' :
                    case 'numfield' :
                    case 'textarea' :
                    case 'date' :
                    case 'hidden' :
                        return field.field_value;
                    case 'checkbox' :
                        return !!field.field_value;
                    case 'dropdown' :
                        return findOptionValueById(field.field_value, field.field_options);
                    case 'radio' :
                        return findOptionValueById(field.field_value, field.field_options);
                    case 'multichoice' :
                        return field.field_value.map(function (value) {
                            return findOptionValueById(value, field.field_options);
                        });
                    case 'separator' :
                        return null;
                }
            }

            var formDomainName = form.form_domain;
            return $http.get(configuration.server + "/form/nurse/"+formDomainName).
                then(function(result) {
                    var domainObject = {};
                    result.data.forEach(function(domainField) {
                        domainObject[domainField.name] = null;
                        var field = findFieldByDomainName(domainField.name, form.form_fields);
                        if (field != null) {
                            domainObject[domainField.name] = extractFieldValue(field);
                        }
                    })
                    return domainObject;
                });
        }
    };
}]);

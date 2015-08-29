module.exports = function(config) {

  // Output directory
  config.dest = 'www';
  
  // Inject cordova script into html
  config.cordova = true;
  
  // Images minification
  config.minify_images = true;

  // Development web server

  config.server.host = '0.0.0.0';
  config.server.port = '8000';
  
  // Set to false to disable it:
  // config.server = false;

  // Weinre Remote debug server
  
  config.weinre.httpPort = 8001;
  config.weinre.boundHost = 'localhost';

  // Set to false to disable it:
  // config.weinre = false;
    
  // 3rd party components
  config.vendor.js.push('bower_components/checklist-model/checklist-model.js');
  config.vendor.js.push('bower_components/ng-table/dist/ng-table.js');
  config.vendor.js.push('bower_components/c3/c3.min.js');
  config.vendor.js.push('bower_components/d3/d3.min.js');
  config.vendor.js.push('uigrid/ui-grid.js');
  // config.vendor.fonts.push('.bower_components/font/dist/*');

};
({
  appDir: 'www',
  dir: 'www-built',
  baseUrl: '.',
  fileExclusionRegExp: /(^example)|(.git)$/,
  //separateCSS: true,
  //buildCSS: false,
  optimizeCss: "node",
  map: {
    '*': {
      css: 'require-css/css'
    }
  },
  modules: [
  {
    name: 'app',
    exclude: ['core-components'],
  },
  {
    name: 'core-components',
    create: true,
    include: ['components/component'], 
    exclude: ['require-css/normalize']
  },
  {
    name: 'popup',
    exclude: ['core-components', 'require-css/normalize']
  }
  ]
  //name: 'app.js',
  //out: 'app-built.js'
})

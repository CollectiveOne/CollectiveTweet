var merge = require('webpack-merge')
var prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  AUTH0_CLIENT_ID: '"DRTvC0O4gIL_6IUpbcxCjeJEVqQCCfxx"',
  AUTH0_DOMAIN: '"collectiveone.auth0.com"'
})

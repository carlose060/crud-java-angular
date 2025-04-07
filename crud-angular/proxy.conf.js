const PROXY_CONFIG = [
  {
    context: [ "/api" ],
    target: "http://localhost:8091/",
    secure: false,
    logLevel: "debug"
  }
];

module.exports = PROXY_CONFIG;

import { EnvironmentPlugin } from 'webpack';

module.exports = {
  plugins: [
    new EnvironmentPlugin(['API_KEY'])
  ]
}

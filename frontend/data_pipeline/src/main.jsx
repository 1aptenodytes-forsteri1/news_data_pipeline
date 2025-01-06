import { createRoot } from 'react-dom/client'
import './index.css'
import store from './global_states/store.js'
import App from './App.jsx'
import { Provider } from 'react-redux';

createRoot(document.getElementById('root')).render(
  <Provider store = {store}>
    <App />
  </Provider>,
)

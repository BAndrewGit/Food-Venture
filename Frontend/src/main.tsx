import React from 'react';
import ReactDOM from 'react-dom/client';
import CssBaseline from '@mui/material/CssBaseline';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import App from './App';
import { AuthProvider } from './context/AuthContext';
import { CartProvider } from './context/CartContext';
import { RestaurantProvider } from './context/RestaurantContext';
import './index.css';

// Optional: Create a custom theme
const theme = createTheme({
    palette: {
        primary: {
            main: '#646cff',
        },
        secondary: {
            main: '#535bf2',
        },
    },
});

// Use the non-null assertion since we know 'root' exists
const rootElement = document.getElementById('root')!;
const root = ReactDOM.createRoot(rootElement);

root.render(
    <React.StrictMode>
        <ThemeProvider theme={theme}>
            <CssBaseline />
            <AuthProvider>
                <CartProvider>
                    <RestaurantProvider>
                        <App />
                    </RestaurantProvider>
                </CartProvider>
            </AuthProvider>
        </ThemeProvider>
    </React.StrictMode>
);
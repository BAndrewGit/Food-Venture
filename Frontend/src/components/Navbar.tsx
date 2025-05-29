import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';

export default function Navbar() {
    const { isAuthenticated, logout, user } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    return (
        <AppBar position="static">
            <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>
                <Typography
                    variant="h6"
                    component={Link}
                    to="/"
                    sx={{ textDecoration: 'none', color: 'inherit', fontWeight: 'bold' }}
                >
                    FoodVenture
                </Typography>

                <Box display="flex" alignItems="center" gap={2}>

                    {isAuthenticated ? (
                        <>
                            <Button color="inherit" component={Link} to="/orders">Comenzile mele</Button>

                            {/* Afișează Admin doar dacă user.role este ADMIN */}
                            {user?.role === 'ADMIN' && (
                                <Button color="inherit" component={Link} to="/admin">Admin</Button>
                            )}

                            <Button
                                color="inherit"
                                onClick={() => navigate('/profile')}
                                sx={{ textTransform: 'none', fontWeight: 'normal' }}
                            >
                                {user?.name || 'Utilizator'}
                            </Button>

                            <Button color="inherit" onClick={handleLogout}>Logout</Button>
                        </>
                    ) : (
                        <>
                            <Button color="inherit" component={Link} to="/login">Login</Button>
                            <Button color="inherit" component={Link} to="/register">Register</Button>
                        </>
                    )}
                </Box>
            </Toolbar>
        </AppBar>
    );
}

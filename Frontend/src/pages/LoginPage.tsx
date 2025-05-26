import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, TextField, Button, Typography } from '@mui/material';
import authService from '../services/authService';

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await authService.login(username, password);
            localStorage.setItem('token', response.token);
            navigate('/');
        } catch {
            alert('Login failed. Check credentials.');
        }
    };

    return (
        <Container maxWidth="xs">
            <Typography variant="h5" gutterBottom>Login</Typography>
            <TextField
                label="Username"
                fullWidth
                margin="normal"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <TextField
                label="Password"
                type="password"
                fullWidth
                margin="normal"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <Button variant="contained" fullWidth onClick={handleLogin}>Login</Button>
        </Container>
    );
};

export default LoginPage;

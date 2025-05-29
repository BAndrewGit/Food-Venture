import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, TextField, Button, Typography } from '@mui/material';
import { useAuth } from '../hooks/useAuth';
import authService from '../services/authService';
import axios from '../services/axios';

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const { login } = useAuth();

    const handleLogin = async () => {
        try {
            const response = await authService.login(username, password);
            const token = response.token;

            // setează token temporar pentru a putea face GET /api/user
            localStorage.setItem('token', token);

            // ia datele utilizatorului
            const userResponse = await axios.get('/user');
            const user = {
                name: userResponse.data.username,
                email: userResponse.data.email,
                role: userResponse.data.role
            };

            login(token, user); // salvează token + user în context
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

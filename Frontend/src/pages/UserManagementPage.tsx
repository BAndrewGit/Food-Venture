import { useEffect, useState } from 'react';
import { Container, Typography, TextField, Button, Alert, Box } from '@mui/material';
import axios from '../services/axios';

export default function UserManagementPage() {
    const [user, setUser] = useState({ username: '', email: '' });
    const [original, setOriginal] = useState({ username: '', email: '' });
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    useEffect(() => {
        axios.get('/user')
            .then(res => {
                setUser({ username: res.data.username, email: res.data.email });
                setOriginal({ username: res.data.username, email: res.data.email });
            })
            .catch(() => setError('Eroare la încărcarea datelor'));
    }, []);

    const handleUpdate = async () => {
        setMessage('');
        setError('');
        try {
            const isUsernameChanged = user.username !== original.username;

            await axios.put('/user', {
                username: user.username,
                email: user.email
            });

            if (isUsernameChanged) {
                // Tokenul vechi nu mai e valid, deci delogare
                localStorage.removeItem('token');
                window.location.href = '/'; // sau '/login'
            } else {
                setMessage('Datele au fost actualizate');
                setOriginal(user);
            }
        } catch (err: any) {
            if (err.response?.status === 409) {
                setError('Username sau email deja există');
            } else {
                setError('Eroare la actualizare');
            }
        }
    };

    const handleDelete = async () => {
        const confirmDelete = confirm('Sigur vrei să ștergi contul? Această acțiune este ireversibilă.');
        if (!confirmDelete) return;

        try {
            await axios.delete('/user');
            localStorage.removeItem('token');
            window.location.href = '/';
        } catch (err) {
            setError('Eroare la ștergerea contului');
        }
    };

    return (
        <Container>
            <Typography variant="h4" gutterBottom>Gestionare cont</Typography>

            <Box display="flex" flexDirection="column" gap={2} maxWidth={400}>
                <TextField
                    label="Username"
                    value={user.username}
                    onChange={e => setUser({ ...user, username: e.target.value })}
                    error={user.username !== original.username && error !== ''}
                    helperText={user.username !== original.username && error ? error : ''}
                />

                <TextField
                    label="Email"
                    type="email"
                    value={user.email}
                    onChange={e => setUser({ ...user, email: e.target.value })}
                    error={user.email !== original.email && error !== ''}
                    helperText={user.email !== original.email && error ? error : ''}
                />

                <Button variant="contained" onClick={handleUpdate}>Salvează modificările</Button>
                <Button variant="outlined" color="error" onClick={handleDelete}>Șterge contul</Button>

                {message && <Alert severity="success">{message}</Alert>}
                {error && <Alert severity="error">{error}</Alert>}
            </Box>
        </Container>
    );
}

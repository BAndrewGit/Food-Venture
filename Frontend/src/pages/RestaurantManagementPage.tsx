import { useEffect, useState } from 'react';
import {
    Container,
    Typography,
    TextField,
    Button,
    Paper,
    Box,
    List,
    ListItem,
    ListItemText,
    Divider,
    IconButton
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import axios from '../services/axios';

type Restaurant = {
    id: number;
    name: string;
    address: string;
};

export default function AdminRestaurantManagementPage() {
    const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
    const [newName, setNewName] = useState('');
    const [newAddress, setNewAddress] = useState('');
    const [editingId, setEditingId] = useState<number | null>(null);
    const [editName, setEditName] = useState('');
    const [editAddress, setEditAddress] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetchRestaurants();
    }, []);

    const fetchRestaurants = async () => {
        try {
            const res = await axios.get('/restaurants');
            setRestaurants(res.data);
        } catch {
            alert('Eroare la încărcarea restaurantelor');
        }
    };

    const handleAddRestaurant = async () => {
        try {
            await axios.post('/restaurants', {
                name: newName,
                address: newAddress
            });
            setNewName('');
            setNewAddress('');
            await fetchRestaurants();
        } catch {
            alert('Eroare la adăugarea restaurantului');
        }
    };

    const handleEdit = (restaurant: Restaurant) => {
        setEditingId(restaurant.id);
        setEditName(restaurant.name);
        setEditAddress(restaurant.address);
    };

    const handleCancelEdit = () => {
        setEditingId(null);
        setEditName('');
        setEditAddress('');
    };

    const handleUpdate = async () => {
        if (editingId === null) return;
        try {
            await axios.put(`/restaurants/${editingId}`, {
                name: editName,
                address: editAddress
            });
            await fetchRestaurants();
            handleCancelEdit();
        } catch {
            alert('Eroare la actualizarea restaurantului');
        }
    };

    const handleDelete = async (id: number) => {
        const confirmDelete = confirm('Sigur vrei să ștergi acest restaurant?');
        if (!confirmDelete) return;
        try {
            await axios.delete(`/restaurants/${id}`);
            await fetchRestaurants();
        } catch {
            alert('Eroare la ștergere');
        }
    };

    return (
        <Container>
            <Typography variant="h4" gutterBottom>Administrare Restaurante</Typography>

            <Paper sx={{ p: 2, mb: 4 }}>
                <Typography variant="h6">Adaugă restaurant nou</Typography>
                <Box display="flex" gap={2} mt={2}>
                    <TextField
                        label="Nume"
                        value={newName}
                        onChange={e => setNewName(e.target.value)}
                    />
                    <TextField
                        label="Adresă"
                        value={newAddress}
                        onChange={e => setNewAddress(e.target.value)}
                    />
                    <Button variant="contained" onClick={handleAddRestaurant}>
                        Adaugă
                    </Button>
                </Box>
            </Paper>

            {editingId !== null && (
                <Paper sx={{ p: 2, mb: 4 }}>
                    <Typography variant="h6">Editează restaurant</Typography>
                    <Box display="flex" gap={2} mt={2}>
                        <TextField
                            label="Nume"
                            value={editName}
                            onChange={e => setEditName(e.target.value)}
                        />
                        <TextField
                            label="Adresă"
                            value={editAddress}
                            onChange={e => setEditAddress(e.target.value)}
                        />
                        <Button variant="contained" onClick={handleUpdate}>
                            Salvează
                        </Button>
                        <Button variant="outlined" onClick={handleCancelEdit}>
                            Anulează
                        </Button>
                    </Box>
                </Paper>
            )}

            <Paper sx={{ p: 2 }}>
                <Typography variant="h6">Lista Restaurante</Typography>
                <List>
                    {restaurants.map((restaurant) => (
                        <Box key={restaurant.id}>
                            <ListItem
                                secondaryAction={
                                    <Box>
                                        <IconButton onClick={() => handleEdit(restaurant)}>
                                            <EditIcon />
                                        </IconButton>
                                        <IconButton onClick={() => handleDelete(restaurant.id)}>
                                            <DeleteIcon />
                                        </IconButton>
                                        <Button
                                            variant="outlined"
                                            onClick={() => navigate(`/admin/restaurants/${restaurant.id}/products`)}
                                            sx={{ ml: 2 }}
                                        >
                                            Gestionează produse
                                        </Button>
                                    </Box>
                                }
                            >
                                <ListItemText
                                    primary={restaurant.name}
                                    secondary={restaurant.address || 'Fără adresă'}
                                />
                            </ListItem>
                            <Divider />
                        </Box>
                    ))}
                </List>
            </Paper>
        </Container>
    );
}

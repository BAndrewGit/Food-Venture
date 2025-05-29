import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import {
    Container, Typography, Select, MenuItem, Box, Paper,
    Button, TextField, IconButton
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import axios from '../services/axios';

type Restaurant = { id: number; name: string };
type Product = { id: number; name: string; price: number; description: string };

export default function ProductManagementPage() {
    const { id } = useParams();
    const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
    const [selectedId, setSelectedId] = useState<number | null>(id ? Number(id) : null);
    const [products, setProducts] = useState<Product[]>([]);
    const [newProduct, setNewProduct] = useState({ name: '', price: '', description: '' });
    const [editingId, setEditingId] = useState<number | null>(null);

    useEffect(() => {
        axios.get('/restaurants')
            .then(res => setRestaurants(res.data))
            .catch(err => console.error('Eroare la restaurante:', err));
    }, []);

    useEffect(() => {
        if (selectedId) {
            axios.get(`/products/restaurant/${selectedId}`)
                .then(res => setProducts(res.data))
                .catch(err => console.error('Eroare la produse:', err));
        } else {
            setProducts([]);
        }
    }, [selectedId]);

    const handleAddOrUpdateProduct = async () => {
        if (!selectedId) return alert('Selectează un restaurant');
        try {
            if (editingId) {
                await axios.put(`/products/${editingId}`, {
                    name: newProduct.name,
                    price: parseFloat(newProduct.price),
                    description: newProduct.description,
                    available: true,
                    imageBase64: null,
                    restaurantId: selectedId
                });
                setEditingId(null);
            } else {
                const response = await axios.post(`/products`, {
                    name: newProduct.name,
                    price: parseFloat(newProduct.price),
                    description: newProduct.description,
                    available: true,
                    imageBase64: null,
                    restaurantId: selectedId
                });
                setProducts([...products, response.data]);
            }
            setNewProduct({ name: '', price: '', description: '' });
            axios.get(`/products/restaurant/${selectedId}`).then(res => setProducts(res.data));
        } catch {
            alert('Eroare la salvarea produsului');
        }
    };

    const handleEdit = (product: Product) => {
        setNewProduct({
            name: product.name,
            price: product.price.toString(),
            description: product.description
        });
        setEditingId(product.id);
    };

    const handleDelete = async (productId: number) => {
        if (!confirm('Sigur vrei să ștergi acest produs?')) return;
        try {
            await axios.delete(`/products/${productId}`);
            setProducts(products.filter(p => p.id !== productId));
        } catch {
            alert('Eroare la ștergerea produsului');
        }
    };

    return (
        <Container>
            <Typography variant="h4" gutterBottom>Gestionare Produse</Typography>

            <Box mt={2} mb={4}>
                <Typography variant="subtitle1">Selectează restaurantul:</Typography>
                <Select
                    fullWidth
                    value={selectedId || ''}
                    onChange={(e) => setSelectedId(Number(e.target.value))}
                >
                    {restaurants.map(r => (
                        <MenuItem key={r.id} value={r.id}>{r.name}</MenuItem>
                    ))}
                </Select>
            </Box>

            {selectedId && (
                <Paper sx={{ p: 3, mb: 4 }}>
                    <Typography variant="h6" gutterBottom>{editingId ? 'Editează produs' : 'Adaugă produs'}</Typography>
                    <Box display="flex" flexDirection="column" gap={2}>
                        <TextField
                            label="Nume"
                            value={newProduct.name}
                            onChange={(e) => setNewProduct({ ...newProduct, name: e.target.value })}
                        />
                        <TextField
                            label="Preț"
                            type="number"
                            value={newProduct.price}
                            onChange={(e) => setNewProduct({ ...newProduct, price: e.target.value })}
                        />
                        <TextField
                            label="Descriere"
                            multiline
                            rows={3}
                            value={newProduct.description}
                            onChange={(e) => setNewProduct({ ...newProduct, description: e.target.value })}
                        />
                        <Button variant="contained" onClick={handleAddOrUpdateProduct}>
                            {editingId ? 'Salvează modificările' : 'Adaugă'}
                        </Button>
                    </Box>
                </Paper>
            )}

            <Typography variant="h6" gutterBottom>Produse existente</Typography>
            {products.length === 0 ? (
                <Typography>Nu există produse pentru acest restaurant.</Typography>
            ) : (
                <Box>
                    {products.map(p => (
                        <Box key={p.id} mb={2} display="flex" justifyContent="space-between" alignItems="center">
                            <Box>
                                <Typography fontWeight="bold">{p.name} – {p.price} lei</Typography>
                                <Typography variant="body2" color="text.secondary">{p.description}</Typography>
                            </Box>
                            <Box>
                                <IconButton onClick={() => handleEdit(p)}><EditIcon /></IconButton>
                                <IconButton onClick={() => handleDelete(p.id)}><DeleteIcon /></IconButton>
                            </Box>
                        </Box>
                    ))}
                </Box>
            )}
        </Container>
    );
}

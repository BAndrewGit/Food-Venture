import { useCart } from '../context/CartContext';
import { useState } from 'react';
import axios from '../services/axios';
import { useNavigate } from 'react-router-dom';
import {
    Container,
    Typography,
    TextField,
    Button,
    List,
    ListItem,
    ListItemText,
    Divider,
    Box
} from '@mui/material';

export default function OrderPage() {
    const { cartItems, totalPrice, clearCart } = useCart();
    const [address, setAddress] = useState('');
    const navigate = useNavigate();

    const handleOrder = async () => {
        if (!address) {
            alert('Adaugă o adresă de livrare!');
            return;
        }

        try {
            await axios.post('/orders', {
                address,
                items: cartItems.map(item => ({
                    productId: item.productId,
                    quantity: item.quantity
                }))
            });
            clearCart();
            alert('Comandă plasată cu succes!');
            navigate('/orders');
        } catch {
            alert('Eroare la plasarea comenzii.');
        }
    };

    return (
        <Container maxWidth="sm">
            <Typography variant="h4" gutterBottom>Finalizează comanda</Typography>

            <List>
                {cartItems.map(item => (
                    <ListItem key={item.productId}>
                        <ListItemText
                            primary={item.name}
                            secondary={`Cantitate: ${item.quantity} | Preț: ${item.price} RON`}
                        />
                    </ListItem>
                ))}
            </List>
            <Divider sx={{ my: 2 }} />

            <Typography variant="h6">Total: {totalPrice.toFixed(2)} RON</Typography>

            <Box mt={2}>
                <TextField
                    label="Adresă de livrare"
                    value={address}
                    onChange={e => setAddress(e.target.value)}
                    fullWidth
                    variant="outlined"
                    margin="normal"
                />
                <Button
                    variant="contained"
                    color="primary"
                    onClick={handleOrder}
                    fullWidth
                    sx={{ mt: 2 }}
                >
                    Plasează comanda
                </Button>
            </Box>
        </Container>
    );
}

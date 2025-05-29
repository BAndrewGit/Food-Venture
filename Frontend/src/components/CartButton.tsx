import { IconButton, Badge } from '@mui/material';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { useNavigate } from 'react-router-dom';
import { useCart } from '../hooks/useCart';

export default function CartButton() {
    const { cartItems } = useCart();
    const navigate = useNavigate();
    const totalItems = cartItems.reduce((sum, item) => sum + item.quantity, 0);

    return (
        <IconButton
            color="primary"
            sx={{ position: 'absolute', top: 16, right: 32 }}
            onClick={() => navigate('/cart')}
        >
            <Badge badgeContent={totalItems} color="error">
                <ShoppingCartIcon />
            </Badge>
        </IconButton>
    );
}

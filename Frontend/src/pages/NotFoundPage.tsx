import { Link } from 'react-router-dom';

export default function NotFoundPage() {
    return (
        <div style={{ textAlign: 'center', marginTop: '100px' }}>
            <h1>404</h1>
            <p>Pagina nu a fost găsită.</p>
            <Link to="/">Înapoi la prima pagină</Link>
        </div>
    );
}

document.addEventListener('DOMContentLoaded', function() {
    // Elementos DOM
    const form = document.getElementById('simulatorForm');
    const resultsContainer = document.getElementById('resultsContainer');
    const emptyState = document.getElementById('emptyState');
    const loadingIndicator = document.getElementById('loadingIndicator');

    // Inicialização do gráfico
    const ctx = document.getElementById('resultsChart').getContext('2d');
    const resultsChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Salário Líquido', 'Beneficios', 'INSS', 'IRPF', 'Outros Descontos'],
            datasets: [{
                data: [0, 0, 0, 0, 0],
                backgroundColor: ['#4e73df', '#e74a3b', '#f6c23e', '#858796', '#010101'],
                hoverBorderColor: "rgba(234, 236, 244, 1)",
            }]
        },
        options: {
            maintainAspectRatio: false,
            plugins: { legend: { display: false } },
            cutout: '70%'
        }
    });

    // Evento de submit
    form.addEventListener('submit', async function(e) {
        e.preventDefault();

        // Mostrar loading
        toggleElements(true);

        try {
            const requestData = getFormData();
            const response = await fetchAPI(requestData);
            displayResults(response);
        } catch (error) {
            handleError(error);
        } finally {
            loadingIndicator.classList.add('d-none');
        }
    });

    // Funções auxiliares
    function toggleElements(loading) {
        emptyState.classList.toggle('d-none', loading);
        resultsContainer.classList.toggle('d-none', loading);
        loadingIndicator.classList.toggle('d-none', !loading);
    }

    function getFormData() {
        return {
            salarioBruto: parseFloat(document.getElementById('salarioBruto').value),
            outrosDescontos: parseFloat(document.getElementById('outrosDescontos').value) || 0,
            beneficios: parseFloat(document.getElementById('beneficios').value) || 0
        };
    }

    async function fetchAPI(data) {
        const response = await fetch('/api/v1/clt', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error(`Erro HTTP: ${response.status}`);
        }
        return await response.json();
    }

    function displayResults(data) {
        // Formatação monetária
        const format = (value) => new Intl.NumberFormat('pt-BR', {
            style: 'currency', currency: 'BRL'
        }).format(value);

        // Atualizar UI
        document.getElementById('salarioLiquido').textContent = format(data.salarioLiquido);
        document.getElementById('resSalarioBruto').textContent = format(data.salarioBruto);
        document.getElementById('resInss').textContent = `- ${format(data.inss)}`;
        document.getElementById('resIrpf').textContent = `- ${format(data.irpf)}`;
        document.getElementById('resOutrosDescontos').textContent = `- ${format(data.outrosDescontos)}`;
        document.getElementById('resBeneficios').textContent = `+ ${format(data.beneficios)}`;
        document.getElementById('resTotalLiquido').textContent = format(data.salarioLiquidoComBeneficios);

        // Atualizar gráfico
        resultsChart.data.datasets[0].data = [
            data.salarioLiquido,
            data.beneficios,
            data.inss,
            data.irpf,
            data.outrosDescontos
        ];
        resultsChart.update();

        toggleElements(false);
    }

    function handleError(error) {
        console.error('Erro:', error);
        const errorMsg = error.message.includes('405')
            ? 'Método não permitido. Verifique a configuração do servidor.'
            : 'Erro ao calcular. Tente novamente.';

        showError(errorMsg);
    }

    function showError(message) {
        let errorAlert = document.getElementById('errorAlert') || createErrorAlert();
        errorAlert.textContent = message;
        errorAlert.classList.remove('d-none');
        setTimeout(() => errorAlert.classList.add('d-none'), 5000);
    }

    function createErrorAlert() {
        const alert = document.createElement('div');
        alert.id = 'errorAlert';
        alert.className = 'alert alert-danger mt-3 d-none';
        form.parentNode.insertBefore(alert, form.nextSibling);
        return alert;
    }
});
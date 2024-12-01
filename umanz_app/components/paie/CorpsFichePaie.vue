<script setup lang="ts">
const headers = [
    { key: 'designation', label: 'Designation' },
    { key: 'nombre', label: 'Nombre' },
    { key: 'taux', label: 'Taux' },
    { key: 'montant', label: 'Montant' },
];

const data = [
    { designation: 'Salaire', taux: 136114.00, montant: 4083409.09, nombre: "1 mois" },
    { designation: 'Absences déductibles', taux: 136114.00, montant: 0, nombre: 0 },
    { designation: 'Primes de rendement', taux: 0, montant: 0, nombre: 0 },
    { designation: 'Primes d\'ancienneté', taux: 0, montant: 0, nombre: 0 },
    { designation: 'Heures supplémentaires majorées de 30%', taux: 30627.00, montant: 0, nombre: 0 },
    { designation: 'Heures supplémentaires majorées de 40%', taux: 32983.00, montant: 0, nombre: 0 },
    { designation: 'Heures supplémentaires majorées de 50%', taux: 35339.00, montant: 0, nombre: 0 },
    { designation: 'Heures supplémentaires majorées de 100%', taux: 47118.00, montant: 0, nombre: 0 },
    { designation: 'Majoration pour heures de nuit', taux: 7068.00, montant: 0, nombre: 0 },
    { designation: 'Primes diverses', taux: 0, montant: 0, nombre: 0 },
    { designation: 'Rappels sur période antérieure', taux: 0, montant: 0, nombre: 0 },
    { designation: 'Droits de congés', taux: 136114.00, montant: 0, nombre: 0 },
    { designation: 'Droits de préavis', taux: 136114.00, montant: 0, nombre: 0 },
    { designation: 'Indemnités de licenciement', taux: 136114.00, montant: 0, nombre: 0 }
];

</script>

<template>
    <div class="px-5">
        <UTable :columns="headers" :rows="data" />

        
    </div>
</template>
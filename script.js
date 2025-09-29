const cards = document.querySelectorAll('.card');

cards.forEach(card => {
  card.addEventListener('click', (event) => {
    // Check if the clicked target is a button or a link
    const target = event.target;

    // If user clicked on download link/button, don't flip the card
    if (target.closest('.download-btn')) return;

    card.classList.toggle('flipped');
  });
});

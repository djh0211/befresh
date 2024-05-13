export function handleEnterKeyPress(event: React.KeyboardEvent, callback: () => void): void {
  if (event.key === 'Enter') {
    callback();
  }
}

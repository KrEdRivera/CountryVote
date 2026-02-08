import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'front-country-voting';

  // Agregar estas propiedades después del title:
private iconRegistry = inject(MatIconRegistry);
private sanitizer = inject(DomSanitizer);

// Agregar este método ngOnInit:
ngOnInit(): void {
  this.iconRegistry.addSvgIcon(
    'check_circle',
    this.sanitizer.bypassSecurityTrustResourceUrl('/icons/check_circle.svg')
  );

  this.iconRegistry.addSvgIcon(
    'cancel_circle',
    this.sanitizer.bypassSecurityTrustResourceUrl('/icons/cancel_circle.svg')
  );
}

}

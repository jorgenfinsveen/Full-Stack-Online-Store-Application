import "./Location.css";

/**
 * A component that previews a given location on Google maps
 *
 * @return {JSX.Element}
 * @constructor
 */
export function Location() {
  return (
    <section className="location-section">
      <h2>You can find us at</h2>
      <iframe
        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4273.443365792914!2d-73.97067357562467!3d40.766846025156845!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c24fa5d33f083b%3A0xc80b8f06e177fe62!2sNew%20York%2C%20USA!5e0!3m2!1sno!2sno!4v1685219584131!5m2!1sno!2sno"
        width="900"
        height="450"
        loading="lazy"
        title="Map"
      ></iframe>
    </section>
  );
}
